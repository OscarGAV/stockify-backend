package test.com.stockify.iam.application.internal.commandservices;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;
import test.com.stockify.iam.application.internal.outboundservices.hashing.HashingService;
import test.com.stockify.iam.application.internal.outboundservices.tokens.TokenService;
import test.com.stockify.iam.domain.model.aggregates.User;
import test.com.stockify.iam.domain.model.commands.SignInCommand;
import test.com.stockify.iam.domain.model.commands.SignUpCommand;
import test.com.stockify.iam.domain.model.valueobjects.Roles;
import test.com.stockify.iam.domain.services.UserCommandService;
import test.com.stockify.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import test.com.stockify.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import test.com.stockify.shared.application.internal.exceptions.InvalidValueException;
import test.com.stockify.shared.application.internal.exceptions.ResourceAlreadyException;
import test.com.stockify.shared.application.internal.exceptions.ResourceNotFoundException;

import java.util.Optional;

@Service
public class UserCommandServiceImpl implements UserCommandService {
    private final UserRepository userRepository;
    private final HashingService hashingService;
    private final TokenService tokenService;
    private final RoleRepository roleRepository;

    public UserCommandServiceImpl(UserRepository userRepository, HashingService hashingService, TokenService tokenService, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.hashingService = hashingService;
        this.tokenService = tokenService;
        this.roleRepository = roleRepository;
    }

    @Override
    public Optional<User> handle(SignUpCommand command) {
        if (userRepository.existsByUsername(command.username()))
            throw new ResourceAlreadyException("User already exists");
        var roles = command.roles();
        if (roles.isEmpty()) {
            var role = roleRepository.findByName(Roles.ROLE_USER);
            roles.add(role.get());
        }
        roles = command.roles().stream()
                .map(role -> roleRepository.findByName(role.getName())
                        .orElseThrow(() -> new ResourceNotFoundException("Role " + command.roles() + " does not exist"))).toList();
        var user = new User(command.username(), hashingService.encode(command.password()), roles, command.email());
        userRepository.save(user);
        return userRepository.findByUsername(command.username());
    }

    @Override
    public Optional<ImmutablePair<User, String>> handle(SignInCommand command) {
        var user = userRepository.findByUsername(command.username());
        if (user.isEmpty()) throw new ResourceNotFoundException("User not found");
        if (!hashingService.matches(command.password(), user.get().getPassword()))
            throw new InvalidValueException("Invalid password");
        var currentUser = user.get();
        var token = tokenService.generateToken(currentUser.getUsername());
        return Optional.of(ImmutablePair.of(currentUser, token));
    }
}

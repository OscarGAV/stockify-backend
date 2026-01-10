package test.com.stockify.iam.application.internal.commandservices;

import org.springframework.stereotype.Service;
import test.com.stockify.iam.domain.model.commands.SeedRolesCommand;
import test.com.stockify.iam.domain.model.entities.Role;
import test.com.stockify.iam.domain.model.valueobjects.Roles;
import test.com.stockify.iam.domain.services.RoleCommandService;
import test.com.stockify.iam.infrastructure.persistence.jpa.repositories.RoleRepository;

import java.util.Arrays;

@Service
public class RoleCommandServiceImpl implements RoleCommandService {
    private final RoleRepository roleRepository;

    public RoleCommandServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void handle(SeedRolesCommand command) {
        Arrays.stream(Roles.values()).forEach(role -> {
            if (!roleRepository.existsByName(role)) {
                roleRepository.save(new Role(Roles.valueOf(role.name())));
            }
        });
    }
}

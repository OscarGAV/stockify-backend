package test.com.stockify.iam.domain.services;

import org.apache.commons.lang3.tuple.ImmutablePair;
import test.com.stockify.iam.domain.model.aggregates.User;
import test.com.stockify.iam.domain.model.commands.SignInCommand;
import test.com.stockify.iam.domain.model.commands.SignUpCommand;

import java.util.Optional;

public interface UserCommandService {
    Optional<User> handle(SignUpCommand command);
    Optional<ImmutablePair<User, String>> handle(SignInCommand command);
}

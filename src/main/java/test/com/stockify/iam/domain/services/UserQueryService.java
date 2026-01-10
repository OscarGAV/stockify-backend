package test.com.stockify.iam.domain.services;

import test.com.stockify.iam.domain.model.aggregates.User;
import test.com.stockify.iam.domain.model.queries.GetAllUsersQuery;
import test.com.stockify.iam.domain.model.queries.GetRoleUserByUserIdQuery;
import test.com.stockify.iam.domain.model.queries.GetUserByIdQuery;
import test.com.stockify.iam.domain.model.queries.GetUserByUsernameQuery;

import java.util.List;
import java.util.Optional;

public interface UserQueryService {
    List<User> handle(GetAllUsersQuery query);
    Optional<User> handle(GetUserByIdQuery query);
    Optional<User> handle(GetUserByUsernameQuery query);
    Optional<String> handle(GetRoleUserByUserIdQuery query);
}

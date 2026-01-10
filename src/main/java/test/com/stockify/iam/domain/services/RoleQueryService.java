package test.com.stockify.iam.domain.services;

import test.com.stockify.iam.domain.model.entities.Role;
import test.com.stockify.iam.domain.model.queries.GetAllRolesQuery;
import test.com.stockify.iam.domain.model.queries.GetRoleByNameQuery;

import java.util.List;
import java.util.Optional;

public interface RoleQueryService {
    List<Role> handle(GetAllRolesQuery query);
    Optional<Role> handle(GetRoleByNameQuery query);
}

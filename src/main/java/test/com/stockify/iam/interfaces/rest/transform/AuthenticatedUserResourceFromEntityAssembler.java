package test.com.stockify.iam.interfaces.rest.transform;

import test.com.stockify.iam.domain.model.aggregates.User;
import test.com.stockify.iam.domain.model.entities.Role;
import test.com.stockify.iam.interfaces.rest.resources.AuthenticatedUserResource;

public class AuthenticatedUserResourceFromEntityAssembler {
    public static AuthenticatedUserResource toResourceFromEntity(User entity, String token) {
        var roles = entity.getRoles().stream().map(Role::getStringName).toList();
        return new AuthenticatedUserResource(entity.getId(), entity.getUsername(), token, roles, entity.getEmail());
    }
}

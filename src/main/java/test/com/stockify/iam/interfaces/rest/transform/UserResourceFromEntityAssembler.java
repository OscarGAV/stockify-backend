package test.com.stockify.iam.interfaces.rest.transform;

import test.com.stockify.iam.domain.model.aggregates.User;
import test.com.stockify.iam.domain.model.entities.Role;
import test.com.stockify.iam.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User entity) {
        var roles = entity.getRoles().stream().map(Role::getStringName).toList();
        return new UserResource(entity.getId(), entity.getUsername(), roles, entity.getEmail());
    }
}

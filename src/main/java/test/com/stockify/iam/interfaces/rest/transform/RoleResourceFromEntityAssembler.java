package test.com.stockify.iam.interfaces.rest.transform;

import test.com.stockify.iam.domain.model.entities.Role;
import test.com.stockify.iam.interfaces.rest.resources.RoleResource;

public class RoleResourceFromEntityAssembler {
    public static RoleResource toResourceFromEntity(Role entity) {
        return new RoleResource(entity.getId(), entity.getStringName());

    }
}

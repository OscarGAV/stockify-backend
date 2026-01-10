package test.com.stockify.iam.interfaces.rest.transform;

import test.com.stockify.iam.domain.model.commands.SignUpCommand;
import test.com.stockify.iam.domain.model.entities.Role;
import test.com.stockify.iam.interfaces.rest.resources.SignUpResource;

import java.util.ArrayList;

public class SignUpCommandFromResourceAssembler {
    public static SignUpCommand toCommandFromResource(SignUpResource resource) {
        var roles = resource.roles() !=null
                ? resource.roles().stream().map(Role::toRoleFromName).toList()
                : new ArrayList<Role>();
        return new SignUpCommand(resource.username(), resource.password(), roles, resource.email());
    }
}

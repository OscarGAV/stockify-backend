package test.com.stockify.iam.interfaces.rest.transform;

import test.com.stockify.iam.domain.model.commands.SignInCommand;
import test.com.stockify.iam.interfaces.rest.resources.SignInResource;

public class SignInCommandFromResourceAssembler {
    public static SignInCommand toCommandFromResource(SignInResource resource) {
        return new SignInCommand(resource.username(), resource.password());
    }
}

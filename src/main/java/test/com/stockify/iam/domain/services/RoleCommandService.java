package test.com.stockify.iam.domain.services;

import test.com.stockify.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
    void handle(SeedRolesCommand command);
}

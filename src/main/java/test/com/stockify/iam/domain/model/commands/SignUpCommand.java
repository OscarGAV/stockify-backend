package test.com.stockify.iam.domain.model.commands;

import test.com.stockify.iam.domain.model.entities.Role;

import java.util.List;

public record SignUpCommand(String username, String password, List<Role> roles, String email) {
}

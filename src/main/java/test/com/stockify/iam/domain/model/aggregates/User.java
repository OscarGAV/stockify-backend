package test.com.stockify.iam.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import test.com.stockify.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import test.com.stockify.iam.domain.model.entities.Role;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends AuditableAbstractAggregateRoot<User> {

    @Getter
    @NotBlank
    @Size(max = 50)
    @Column(unique = true)
    private String username;

    @Setter
    @Getter
    @NotBlank
    @Size(max = 120)
    private String password;

    @Email
    @Getter
    @Size(max = 360)
    @Column(unique = true)
    private String email;

    @Getter
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private final Set<Role> roles;

    public User() { this.roles = new HashSet<>();}

    public User(String username, String password, String email) {
        this();
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User(String username, String password, List<Role> roles, String email) {
        this(username, password, email);
        addRoles(roles);
    }

    public void addRoles(List<Role> roles) {
        var validatedRoles = Role.validateRoleSet(roles);
        this.roles.addAll(validatedRoles);
    }
}

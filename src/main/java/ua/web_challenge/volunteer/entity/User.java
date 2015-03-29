package ua.web_challenge.volunteer.entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created on 25.03.2015
 *
 * @author Bohdan Vanchuhov
 */
@Entity
@Table(name = "users")
@NamedQueries(
        @NamedQuery(name = "User.findByName",
                query = "SELECT u FROM User u WHERE u.username = :username")
)
public class User {
    private int id;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9_-]{3,15}$")
    private String username;

    @NotEmpty
    private String password;

    private boolean accountEnabled = false;

    @NotNull
    private Email email;

    private List<UserRole> userRoles = new ArrayList<>();

    public User() {
    }

    public User(String username) {
        this.username = username;
    }

    //----- Getters and Setters -----

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username", length = 15, nullable = false, unique = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password", length = 32, nullable = false)
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @OneToOne
    @JoinColumn(name = "email_id", nullable = false)
    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    @ElementCollection(targetClass = UserRole.class, fetch = EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "user_role", length = 15)
    public List<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public void addUserRole(UserRole userRole) {
        userRoles.add(userRole);
    }

    @Basic
    @Column(name = "enabled")
    @JsonGetter(value = "enabled")
    public boolean isAccountEnabled() {
        return accountEnabled;
    }

    public void setAccountEnabled(boolean accountEnabled) {
        this.accountEnabled = accountEnabled;
    }
}

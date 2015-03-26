package ua.web_challenge.volunteer.entity;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.List;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;
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

    @Pattern(regexp = " ^[a-z0-9_-]{3,15}$")
    private String username;

    private String password;

    private List<UserRole> userRoles;

    public User() {
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
    @Column(name = "password", length = 128, nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}

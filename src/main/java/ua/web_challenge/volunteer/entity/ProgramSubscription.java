package ua.web_challenge.volunteer.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created on 29.03.2015
 *
 * @author Bohdan Vanchuhov
 */
@Entity
@Table(name = "subscriptions")
public class ProgramSubscription {
    private int id;

    @NotNull
    private Email email;

    private User user;

    public ProgramSubscription() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @OneToOne
    @JoinColumn(name = "email_id", nullable = false, unique = true)
    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    @OneToOne
    @JoinColumn(name = "user_id", nullable = true)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

package ua.web_challenge.volunteer.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;
import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.GenerationType.SEQUENCE;

/**
 * Created on 29.03.2015
 *
 * @author Bohdan Vanchuhov
 */
@Entity
@Table(name = "emails")
public class Email {
    private int id;

    @org.hibernate.validator.constraints.Email
    @JsonProperty("email")
    private String value;

    public Email() {
    }

    public Email(String value) {
        this.value = value;
    }

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "value", length = 50, nullable = false)
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

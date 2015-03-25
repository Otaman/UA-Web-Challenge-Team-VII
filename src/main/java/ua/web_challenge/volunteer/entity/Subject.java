package ua.web_challenge.volunteer.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

/**
 * The subject of volunteer programs
 *
 * @author Bohdan Vanchuhov
 */
@Entity
@Table(name = "subjects")
public class Subject {
    private int id;

    @NotEmpty
    private String name;

    public Subject() {
    }

    //----- Getters and Setters -----

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
    @Column(name = "name", length = 50, nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

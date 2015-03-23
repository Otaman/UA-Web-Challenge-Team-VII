package ua.web_challenge.volunteer.entity;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.AUTO;

/**
 * The volunteer program
 * @author Bohdan Vanchuhov
 */
@Entity
@Table(name = "programs")
public class VolunteerProgram {
    private int id;
    private String name;
    private Subject subject;
    private List<AssistanceType> assistanceTypes;
    private List<Location> locations;
    private String description;

    public VolunteerProgram() {
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
    @Column(name = "name", length = 80, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    @JoinColumn(name = "subject_id")
    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @ElementCollection(targetClass = AssistanceType.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "assistance_types")
    @Column(name = "name")
    public List<AssistanceType> getAssistanceTypes() {
        return assistanceTypes;
    }

    public void setAssistanceTypes(List<AssistanceType> assistanceTypes) {
        this.assistanceTypes = assistanceTypes;
    }

    @OneToMany(fetch = LAZY)
    @JoinTable(name = "program_locations",
            joinColumns = @JoinColumn(name = "program_id"),
            inverseJoinColumns = @JoinColumn(name = "location_id"))
    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    @Basic(fetch = LAZY)
    @Lob
    @Column(name = "description", length = 2000)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

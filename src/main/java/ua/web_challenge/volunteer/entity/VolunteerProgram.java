package ua.web_challenge.volunteer.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * The volunteer program
 *
 * @author Bohdan Vanchuhov
 */
@Entity
@Table(name = "programs")
public class VolunteerProgram {
    private int id;
    private String name;
    private Subject subject;

    @JsonProperty("assistance_type")
    private List<AssistanceType> assistanceTypes = new ArrayList<>();

    private List<String> locations = new ArrayList<>();
    private String description;

    public VolunteerProgram() {
    }

    public VolunteerProgram(String name) {
        this.name = name;
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

    @ElementCollection(targetClass = AssistanceType.class, fetch = EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(
            name = "assistance_types",
            joinColumns = @JoinColumn(name = "program_id"))
    @Column(name = "assistance_type", length = 30)
    public List<AssistanceType> getAssistanceTypes() {
        return assistanceTypes;
    }

    public void setAssistanceTypes(List<AssistanceType> assistanceTypes) {
        this.assistanceTypes = assistanceTypes;
    }

    public void addAssistanceType(AssistanceType assistanceType) {
        assistanceTypes.add(assistanceType);
    }

    @ElementCollection(targetClass = String.class, fetch = EAGER)
    @CollectionTable(
            name = "program_locations",
            joinColumns = @JoinColumn(name = "program_id")
    )
    @Column(name = "location", length = 50, nullable = false)
    public List<String> getLocations() {
        return locations;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
    }

    public void addLocation(String location) {
        locations.add(location);
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

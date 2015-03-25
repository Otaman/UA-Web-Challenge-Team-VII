package ua.web_challenge.volunteer.entity;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.AUTO;
import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.TemporalType.TIMESTAMP;

/**
 * The news about volunteer programs
 *
 * @author Bohdan Vanchuhov
 */
@Entity
@Table(name = "news")
public class VolunteerNews {
    private int id;
    private Date creationDate;
    private VolunteerProgram program;
    private String description;

    public VolunteerNews() {
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

    @Temporal(TIMESTAMP)
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "program_id")
    public VolunteerProgram getProgram() {
        return program;
    }

    public void setProgram(VolunteerProgram program) {
        this.program = program;
    }

    @Basic(fetch = LAZY)
    @Lob
    @Column(name = "description", length = 2000, nullable = true)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

package ua.web_challenge.volunteer.entity;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

/**
 * The location of volunteer programs
 * @author Bohdan Vanchuhov
 */
@Entity
@Table(name = "locations")
public class Location {
    private int id;
    private String cityName;
    private String countryName;

    public Location() {
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
    @Column(name = "city", length = 30, nullable = false)
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Basic
    @Column(name =  "country", length = 30, nullable = false)
    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}

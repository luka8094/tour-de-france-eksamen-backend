package com.example.tourdefrance.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

//Klasse til at enkapsle en énkelt rytter fra et hold
@Entity
@Table(name = "riders")
public class Rider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    // fornavn : små bogstaver (lower case)
    @Column(nullable = false)
    String firstName;

    // efternavn : KAPITAL BOGSTAVER (upper case)
    @Column(nullable = false)
    String lastName;

    // rytteren's land
    @Enumerated(EnumType.STRING)
    @Column(name="country",nullable = true)
    Country country;

    // det samlet antal point en enkelt rytter har samlet
    int totalPoints;

    //Flere ryttere kan knyttes til et hold (team)
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "team_id")
    Team team;

    public Rider(){ }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        //formartere efternavn (lastname) til lower case for sikkerhedskyld
        this.firstName = firstName.toLowerCase();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        //formartere efternavn (lastname) til upper case for sikkerhedskyld
        this.lastName = lastName.toUpperCase();
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}

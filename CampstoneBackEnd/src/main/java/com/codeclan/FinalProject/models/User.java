package com.codeclan.FinalProject.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String username;
    private String password;
    private String profilePicture;
    private Long sustainability_points;

    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Route> routes = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<SustainabilityAction> sustainabilityActions = new ArrayList<>();

    public List<SustainabilityAction> getSustainabilityActions() {
        return sustainabilityActions;
    }

    public User(String username, String password, String profilePicture, Long sustainability_points) {
        this.username = username;
        this.password = password;
        this.profilePicture = profilePicture;
        this.sustainability_points = sustainability_points;
    }

    public User(){}

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Long getSustainability_points() {
        return sustainability_points;
    }

    public void setSustainability_points(Long sustainability_points) {
        this.sustainability_points = sustainability_points;
    }

    public Long getSustainabilityPoints() {
        return null;
    }
}

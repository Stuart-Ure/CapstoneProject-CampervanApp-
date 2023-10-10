package com.codeclan.FinalProject.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;
    private String username;
    private String password_hash;
    private String profile_picture;
    private Long sustainability_points;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Route> routes = new ArrayList<>();

    public User(String username, String password_hash, String profile_picture, Long sustainability_points) {
        this.username = username;
        this.password_hash = password_hash;
        this.profile_picture = profile_picture;
        this.sustainability_points = sustainability_points;
    }

    public User(){}

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }


    public String getProfile_picture() {
        return profile_picture;
    }

    public void setProfile_picture(String profile_picture) {
        this.profile_picture = profile_picture;
    }

    public Long getSustainability_points() {
        return sustainability_points;
    }

    public void setSustainability_points(Long sustainability_points) {
        this.sustainability_points = sustainability_points;
    }
}

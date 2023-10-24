package com.codeclan.FinalProject.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sustainability_actions")
public class SustainabilityAction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long action_id;
    private String name;
    private Long points;

    @JsonIgnoreProperties("sustainabilityActions")
    @ManyToMany(mappedBy = "sustainabilityActions")
    private List<User> users = new ArrayList<>();

    public SustainabilityAction(Long action_id, String name, Long points) {
        this.action_id = action_id;
        this.name = name;
        this.points = points;
    }

    public SustainabilityAction(){}

    public SustainabilityAction(String name, long l) {
    }

    public Long getAction_id() {
        return action_id;
    }

    public void setAction_id(Long action_id) {
        this.action_id = action_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPoints() {
        return points;
    }

    public void setPoints(Long points) {
        this.points = points;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "SustainabilityActions{" +
                "action_id=" + action_id +
                ", name='" + name + '\'' +
                ", points=" + points +
                '}';
    }
}
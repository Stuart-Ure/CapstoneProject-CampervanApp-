package com.codeclan.FinalProject.models;


import javax.persistence.*;

@Entity
@Table(name = "sustainability_actions")
public class SustainabilityActions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long action_id;
    private String name;
    private Long points;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public SustainabilityActions(Long action_id, String name, Long points) {
        this.action_id = action_id;
        this.name = name;
        this.points = points;
    }

    public SustainabilityActions(){}

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
}
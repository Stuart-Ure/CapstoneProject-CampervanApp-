package com.codeclan.FinalProject.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "routes")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long route_id;
    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL)
    private List<Destination> destinations = new ArrayList<>();

    public Route(Long route_id,  String name, String description) {
        this.route_id = route_id;
        this.name = name;
        this.description = description;
    }

    public Route(){}



    public Long getRoute_id() {
        return route_id;
    }

    public void setRoute_id(Long route_id) {
        this.route_id = route_id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Destination> getDestinations() {
        return destinations;
    }
}

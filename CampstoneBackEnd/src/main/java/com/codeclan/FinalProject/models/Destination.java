package com.codeclan.FinalProject.models;

import javax.persistence.*;

@Entity
@Table(name = "destinations")

public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long destination_id;
    private String name;
    private Double latitude;
    private Double longitude;
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "route_id") // The name of the foreign key column in the destination table
    private Route route;

    public Destination(String name, Double latitude, Double longitude, String description) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
    }
    public Destination(){}

    public Long getDestination_id() {
        return destination_id;
    }

    public void setDestination_id(Long destination_id) {
        this.destination_id = destination_id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getDescription() {
        return description;
    }


    public void setRoute(Route route) {
        this.route = route;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
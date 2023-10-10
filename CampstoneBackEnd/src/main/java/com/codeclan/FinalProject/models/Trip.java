//package com.codeclan.FinalProject.models;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "trip")
//public class Trip {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long trip_id;
//    private Long destination_id;
//    private Long route_id;
//
//    public Trip(Long trip_id, Long destination_id, Long route_id) {
//        this.trip_id = trip_id;
//        this.destination_id = destination_id;
//        this.route_id = route_id;
//    }
//
//    public Trip (){}
//
//    public Long getTrip_id() {
//        return trip_id;
//    }
//
//    public void setTrip_id(Long trip_id) {
//        this.trip_id = trip_id;
//    }
//
//    public Long getDestination_id() {
//        return destination_id;
//    }
//
//    public void setDestination_id(Long destination_id) {
//        this.destination_id = destination_id;
//    }
//
//    public Long getRoute_id() {
//        return route_id;
//    }
//
//    public void setRoute_id(Long route_id) {
//        this.route_id = route_id;
//    }
//}
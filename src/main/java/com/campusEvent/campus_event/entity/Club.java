package com.campusEvent.campus_event.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Club {

    @Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = "Club_ID")
    long clubID;
    @Column(unique = true)
    String clubName;
    @OneToMany(mappedBy = "club")
    private List<Event> events;
}

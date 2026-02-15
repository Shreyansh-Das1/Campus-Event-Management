package com.campusEvent.campus_event.entity;

import jakarta.persistence.*;

@Entity
public class Club {

    @Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = "Club ID")
    int clubID;
    @Column(unique = true)
    String clubName;
}

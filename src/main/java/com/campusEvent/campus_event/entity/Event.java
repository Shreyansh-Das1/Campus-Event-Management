package com.campusEvent.campus_event.entity;

import jakarta.persistence.*;

//Incomplete
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Eventid;

    @Enumerated(EnumType.STRING)
    EventStatus EventStatus;
}

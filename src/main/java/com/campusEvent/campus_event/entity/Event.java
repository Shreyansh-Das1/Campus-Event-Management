package com.campusEvent.campus_event.entity;

import com.campusEvent.campus_event.entity.enums.EventStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="Event ID")
    private int Eventid;

    @Column
    String title, description, venue;

    @Column
    int capacity;

    @ManyToOne(fetch = FetchType.LAZY)
    private Club club;

    @Column
    @Enumerated(EnumType.STRING)
    EventStatus EventStatus;
}

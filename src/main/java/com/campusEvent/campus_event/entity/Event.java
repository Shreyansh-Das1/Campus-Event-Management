package com.campusEvent.campus_event.entity;

import com.campusEvent.campus_event.entity.enums.EventStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.*;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="Event_ID")
    private long Eventid;

    @Column
    String title, description, venue;

    @Column
    int capacity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "Club_ID")
    private Club club;

    @Column
    LocalDateTime date;

    @Column
    LocalTime startTime, endTime;

    @Column
    @Enumerated(EnumType.STRING)
    EventStatus EventStatus;
}

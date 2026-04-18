package com.campusEvent.campus_event.entity;

import com.campusEvent.campus_event.entity.enums.EventStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.*;

@Getter
@Setter
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="Event_ID")
    private long Eventid;

    @Column private String title, description, venue;

    @Column private int capacity;
    @Column private int booked = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "Club_ID")
    private Club club;

    @Column private LocalDateTime date;

    @Column private LocalTime startTime, endTime;

    @Version private Long version;
    @Column
    @Enumerated(EnumType.STRING)
    private EventStatus eventStatus;

}

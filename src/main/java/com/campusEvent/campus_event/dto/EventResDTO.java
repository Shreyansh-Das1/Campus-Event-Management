package com.campusEvent.campus_event.dto;

import com.campusEvent.campus_event.entity.Club;
import com.campusEvent.campus_event.entity.enums.EventStatus;
import jakarta.persistence.*;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Setter
public class EventResDTO {
    private long Eventid;
    private String title, description, venue;
    private int capacity;
    private String clubName;
    private LocalDateTime date;
    private LocalTime startTime, endTime;
    private EventStatus EventStatus;
}
/*id

title

description

capacity

eventDate

startTime

endTime

venue

status

clubName*/

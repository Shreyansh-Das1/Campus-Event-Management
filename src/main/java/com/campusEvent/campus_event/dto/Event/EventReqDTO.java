package com.campusEvent.campus_event.dto.Event;

import lombok.Getter;

import java.time.LocalDateTime;
import java.time.LocalTime;
@Getter
public class EventReqDTO {
    private String title, description, venue;
    private int capacity;
    private long clubID;
    private LocalDateTime date;
    private LocalTime startTime, endTime;
}
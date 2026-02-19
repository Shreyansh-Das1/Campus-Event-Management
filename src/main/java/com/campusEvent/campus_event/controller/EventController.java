package com.campusEvent.campus_event.controller;

import com.campusEvent.campus_event.dto.ClubResDTO;
import com.campusEvent.campus_event.dto.EventReqDTO;
import com.campusEvent.campus_event.dto.EventResDTO;
import com.campusEvent.campus_event.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller("/event")
public class EventController {

    @Autowired
    EventService eventService;

    @PostMapping("/create")
    public @ResponseBody EventResDTO createEvent(@RequestBody EventReqDTO erqdto) {
        return eventService.createEvent(erqdto);
    }

    @GetMapping
    public @ResponseBody List<EventResDTO> getEvents(@RequestParam Long clubID) {
        return eventService.getAllEvents();
    }
}

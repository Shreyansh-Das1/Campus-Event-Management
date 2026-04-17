package com.campusEvent.campus_event.controller;

import com.campusEvent.campus_event.dto.Event.EventReqDTO;
import com.campusEvent.campus_event.dto.Event.EventResDTO;
import com.campusEvent.campus_event.dto.Event.RegistrationDTO;
import com.campusEvent.campus_event.entity.Event;
import com.campusEvent.campus_event.entity.enums.EventStatus;
import com.campusEvent.campus_event.entity.enums.Role;
import com.campusEvent.campus_event.relations.Registration;
import com.campusEvent.campus_event.service.EventService;
import com.campusEvent.campus_event.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/event")
public class EventController {

    @Autowired
    EventService eventService;
    @Autowired
    RegistrationService registrationService;
    @PreAuthorize("hasRole('ORGANIZER')")
    @PostMapping("/create")
    public @ResponseBody EventResDTO createEvent(@RequestBody EventReqDTO erqdto) {
        return eventService.createEvent(erqdto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/approval")
    public @ResponseBody EventResDTO approveEvent(@PathVariable long id, @RequestBody EventStatus es) {
        return eventService.eventApproval(id, es);
    }

    @GetMapping("/getappr")
    public @ResponseBody List<Event> getApproved() {
        return eventService.getEvents();
    }

    @GetMapping
    public @ResponseBody List<EventResDTO> getEventsByCLub(@RequestParam Long clubID) {
        return eventService.getAllEvents();
    }

    @PutMapping("/reserve")
    @PreAuthorize("hasRole('STUDENT')")
    public @ResponseBody RegistrationDTO reserveEvent(@RequestBody long eventId) {
        return registrationService.reserveSeat(eventId);
    }
}

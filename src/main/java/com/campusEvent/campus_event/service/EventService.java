package com.campusEvent.campus_event.service;

import com.campusEvent.campus_event.dto.Event.*;
import com.campusEvent.campus_event.entity.Event;
import com.campusEvent.campus_event.entity.enums.EventStatus;
import com.campusEvent.campus_event.repository.EventRepo;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {
    @Autowired
    private EventRepo eventrepo;


    @Autowired
    private ClubService cs;
    public EventResDTO createEvent(EventReqDTO erqdto){
        if(!cs.existsByClubId(erqdto.getClubID()))
            return null;
        Event newEv = mapFroDTO(erqdto);
        return mapToDTO(newEv);
    }

    public List<EventResDTO> getAllClubEvents(Long clubID) {
        List<Event> ev = eventrepo.findByClub_ClubId(clubID);
        List<EventResDTO> res = new ArrayList<>();
        for (Event e : ev)
            res.add(mapToDTO(e));
        return res;
    }
    public EventResDTO eventApproval(Long id, EventStatus es)
    {

        Event ev = eventrepo.findById(id)
                .orElseThrow(()->new RuntimeException("Event Not Found"));
        ev.setEventStatus(es);
        eventrepo.save(ev);
        return  mapToDTO(ev);
    }

  public List<Event> getEvents(){

      @Nullable Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      switch (auth.getAuthorities().iterator().next().getAuthority()){
        case "ROLE_ADMIN":
            return eventrepo.findAll();
        case "ROLE_STUDENT":
            return eventrepo.findByEventStatus(EventStatus.APPROVED);
        default:
                List<Event> evs = eventrepo.findByEventStatus(EventStatus.APPROVED);
                evs.addAll(eventrepo.findByEventStatus(EventStatus.PENDING));
                return evs;
        }
    }

    private EventResDTO mapToDTO(Event event) {
        EventResDTO dto = new EventResDTO();
        dto.setEventid(event.getEventid());
        dto.setTitle(event.getTitle());
        dto.setEventStatus(event.getEventStatus());
        dto.setClubName(event.getClub().getClubName());
        return dto;
    }
    private Event mapFroDTO(EventReqDTO erqdto)
    {
        Event e = new Event();
        e.setEventStatus(EventStatus.PENDING);
        e.setDescription(erqdto.getDescription());
        e.setDate(erqdto.getDate());
        e.setStartTime(erqdto.getStartTime());
        e.setEndTime(erqdto.getEndTime());
        e.setVenue(erqdto.getVenue());
        e.setCapacity(erqdto.getCapacity());
        e.setClub(cs.findClubById(erqdto.getClubID()));
        e.setTitle(erqdto.getTitle());
        e.setBooked(0);
        eventrepo.save(e);
        return e;
    }
}
/*Methods:

createEvent(EventRequestDTO)

getAllEvents()

getApprovedEvents()

Inside createEvent:

Fetch club by ID

Set status = PENDING

Save event*/

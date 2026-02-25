package com.campusEvent.campus_event.service;

import com.campusEvent.campus_event.dto.Event.EventReqDTO;
import com.campusEvent.campus_event.dto.Event.EventResDTO;
import com.campusEvent.campus_event.entity.Event;
import com.campusEvent.campus_event.entity.enums.EventStatus;
import com.campusEvent.campus_event.repository.EventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {
    @Autowired
    private EventRepo Eventrepo;
    @Autowired
    private ClubService cs;
    public EventResDTO createEvent(EventReqDTO erqdto){
        if(!cs.existsByClubId(erqdto.getClubID()))
            return null;
        Event newEv = mapFroDTO(erqdto);
        return mapToDTO(newEv);
    }

    public List<EventResDTO> getAllEvents() {
        List<Event> ev = Eventrepo.findAll();
        List<EventResDTO> res = new ArrayList<>();
        for (Event e : ev)
            res.add(mapToDTO(e));
        return res;
    }
   /* public List<EventResDTO> getAllEvents(Long clubID) {
        List<Event> ev = Eventrepo.findByClub(clubID);
        List<EventResDTO> res = new ArrayList<>();
        for (Event e : ev)
            res.add(mapToDTO(e));
        return res;
    }*/

    public List<Event> getApprovedEvents(){ return Eventrepo.findByEventStatus(EventStatus.APPROVED); }

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
        Eventrepo.save(e);
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

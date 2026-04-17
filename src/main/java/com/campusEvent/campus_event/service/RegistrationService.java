package com.campusEvent.campus_event.service;

import com.campusEvent.campus_event.dto.Event.RegistrationDTO;
import com.campusEvent.campus_event.entity.Event;
import com.campusEvent.campus_event.entity.User;
import com.campusEvent.campus_event.entity.enums.RegisStatus;
import com.campusEvent.campus_event.relations.Registration;
import com.campusEvent.campus_event.repository.EventRepo;
import com.campusEvent.campus_event.repository.RegisRepo;
import com.campusEvent.campus_event.repository.UserRepo;
import jakarta.transaction.Transactional;
import lombok.SneakyThrows;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class RegistrationService {

    @Autowired
    private EventRepo eventrepo;
    @Autowired
    private RegisRepo regisRepo;

    Event e;

    @Nullable Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String role = auth.getAuthorities().iterator().next().getAuthority();

    @Transactional
    @SneakyThrows //Used to declare multiple checked errors could be thrown
    public RegistrationDTO reserveSeat(Long eventId) {
        Long userId = Long.parseLong(auth.getName());
        if(!eventrepo.existsById(eventId))
            throw new IllegalStateException("Event Not Found");
        if(!role.equals("ROLE_STUDENT"))
            throw new IllegalAccessException(role+" not allowed");
        if( regisRepo.existsByUser(userId, eventId))
            throw new IllegalStateException("User already registered");
        e =  eventrepo.findById(eventId).get();
        if(eventrepo.registerSeat(eventId) == 0)
            throw new IllegalStateException("Event Full");
        else
            return mapToDTO(eventId, userId);

    }
    private RegistrationDTO mapToDTO(Long eventId, Long userId) {
        Registration reg = new Registration();
        reg.setUser((User) auth.getPrincipal());
        Event ev = eventrepo.findById(eventId).get();
        reg.setEvent(ev);
        reg.setRs(RegisStatus.ACTIVE);
        regisRepo.save(reg);

        RegistrationDTO dto = new RegistrationDTO();
        dto.setTicketId( reg.getRegistrationId());
        dto.setRs(reg.getRs());
        dto.setEvTitle(ev.getTitle());
        return dto;
    }
}

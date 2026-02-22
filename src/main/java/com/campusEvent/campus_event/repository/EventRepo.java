package com.campusEvent.campus_event.repository;

import com.campusEvent.campus_event.entity.Event;
import com.campusEvent.campus_event.entity.enums.EventStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepo extends JpaRepository<Event,Long> {
    List<Event> findByEventStatus(EventStatus Eventstatus);
    //List<Event> findByClub(Long club_id);
    List<Event> findAll();
}

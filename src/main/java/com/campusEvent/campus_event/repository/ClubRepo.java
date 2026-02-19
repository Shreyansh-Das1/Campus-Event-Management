package com.campusEvent.campus_event.repository;

import com.campusEvent.campus_event.entity.Club;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClubRepo extends JpaRepository<Club,Long> {
    boolean existsByClubID(Long clubId);
    Club findByClubID(Long clubId);

    boolean existsByClubName(String clubName);
}

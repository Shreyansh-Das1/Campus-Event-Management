package com.campusEvent.campus_event.repository;

import com.campusEvent.campus_event.relations.Membership;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembershipRepo extends JpaRepository<Membership, Long> {
    boolean existsByClubAndUser(Long clubId, Long userId);
}

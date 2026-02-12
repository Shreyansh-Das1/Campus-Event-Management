package com.campusEvent.campus_event.repository;

import com.campusEvent.campus_event.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends JpaRepository<User, Integer> {

    User findByEmail(String email);

    boolean existsByEmail(String email);
}

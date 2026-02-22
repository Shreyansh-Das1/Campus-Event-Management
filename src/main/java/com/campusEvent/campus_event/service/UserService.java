package com.campusEvent.campus_event.service;

import com.campusEvent.campus_event.dto.UserDTO;
import com.campusEvent.campus_event.entity.User;
import com.campusEvent.campus_event.repository.UserRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    UserRepo ur;

    public String registerUser(UserDTO udto) throws Exception{
        if(ur.existsByEmail(udto.getEmail()))
            throw new Exception(udto.getEmail()+" Already in Use");
        User user = new User();
        BeanUtils.copyProperties(udto,user);
        user.setPassword(passwordEncoder.encode(udto.getPassword()));
        ur.save(user);
        return "User Created";
    }
}

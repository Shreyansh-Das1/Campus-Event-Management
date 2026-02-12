package com.campusEvent.campus_event.dto;

import com.campusEvent.campus_event.entity.Role;
import jdk.jfr.DataAmount;
import lombok.Data;

@Data
public class UserDTO {
    private int id;
    private String name;
    private String email;
    private String number;
    private String password;
    private Role role;
}

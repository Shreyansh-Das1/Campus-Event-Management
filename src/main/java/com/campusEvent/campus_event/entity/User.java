package com.campusEvent.campus_event.entity;

import com.campusEvent.campus_event.entity.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Getter @Setter
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="User ID")
    private int id;

    @Column(name="Name", nullable = false)
    private String name;

    @Column(name="Email",unique = true, nullable = false)
    private String email;

    @Column(name="Ph. Number",unique = true, nullable = false)
    private String number;

    @JsonIgnore @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING) @NotNull
    private Role role;
}

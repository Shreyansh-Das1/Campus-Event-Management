package com.campusEvent.campus_event.entity;

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
    private int id;

    @Column(name="Name") @NotNull
    private String name;

    @Column(name="Email",unique = true) @NotNull
    private String email;

    @Column(name="Ph. Number",unique = true) @NotNull
    private String number;

    @JsonIgnore @NotNull
    private String password;

    @Enumerated(EnumType.STRING) @NotNull
    private Role role;
}

package com.campusEvent.campus_event.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
public class Club {

    @Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = "Club_ID")
    long clubID;
    @Column(unique = true)
    String clubName;
    @OneToMany(mappedBy = "club") @Nullable
    private List<Event> events;
}

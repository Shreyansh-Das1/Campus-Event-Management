package com.campusEvent.campus_event.relations;

import com.campusEvent.campus_event.entity.Event;
import com.campusEvent.campus_event.entity.User;
import jakarta.persistence.*;

@Entity
@Table(
        name = "Registration",
    uniqueConstraints= { @UniqueConstraint(columnNames = {"User", "Event"}) }
)
public class Registration {

    @Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name="TicketID")
    int ticketId;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name="User ID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name="Event ID")
    private Event event;

}

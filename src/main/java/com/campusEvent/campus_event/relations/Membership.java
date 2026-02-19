package com.campusEvent.campus_event.relations;

import com.campusEvent.campus_event.entity.Club;
import com.campusEvent.campus_event.entity.enums.ClubSeniority;
import com.campusEvent.campus_event.entity.User;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
public class Membership {

    @EmbeddedId @Column
    MembershipID id;

    @JoinColumn(name = "User ID")
    @ManyToOne(fetch= FetchType.LAZY)
    private User user;

    @JoinColumn(name = "Club ID")
    @ManyToOne(fetch= FetchType.LAZY)
    private Club club;
    @Column
    ClubSeniority cs;
}
@Data
@Embeddable
class MembershipID implements Serializable
{
    String uID, cID;

    public  MembershipID(String uID, String cID)
    {
        this.uID = uID;
        this.cID = cID;
    }

    public MembershipID(){}
}

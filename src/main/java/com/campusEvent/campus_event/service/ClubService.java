package com.campusEvent.campus_event.service;

import com.campusEvent.campus_event.dto.Club.*;
import com.campusEvent.campus_event.entity.Club;
import com.campusEvent.campus_event.repository.ClubRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClubService {

    @Autowired
    private ClubRepo clubrepo;

    public ClubResDTO createClub(ClubReqDTO crqdto){
        Club newC = new Club();
        if(clubrepo.existsByClubName(crqdto.getClubName()))
            return null;
        newC.setClubName(crqdto.getClubName());
        clubrepo.save(newC);
        return maptoResDTO(newC);
    }

    public List<Club> getAllClubs(){ return clubrepo.findAll(); }

    public boolean existsByClubId(Long clubId){ return clubrepo.existsById(clubId);  }

    public Club findClubById(Long clubId){
        return clubrepo.findById(clubId).get();
    }
    public ClubResDTO retClub(Long clubId){
        Club c = clubrepo.findById(clubId).get();
        return maptoResDTO(c);
    }

    ClubResDTO maptoResDTO(Club club){
        ClubResDTO crdto = new ClubResDTO();
        crdto.setClubName(club.getClubName());
        crdto.setClubID(club.getClubId());
        return crdto;
    }
}

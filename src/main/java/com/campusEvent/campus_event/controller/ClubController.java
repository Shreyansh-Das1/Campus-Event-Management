package com.campusEvent.campus_event.controller;

import com.campusEvent.campus_event.dto.ClubReqDTO;
import com.campusEvent.campus_event.dto.ClubResDTO;
import com.campusEvent.campus_event.entity.Club;
import com.campusEvent.campus_event.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController("/api/club")
public class ClubController {

    @Autowired
    ClubService clubService;

    @PostMapping("/createclub")
    public @ResponseBody ClubResDTO createClub(@RequestBody ClubReqDTO club) {
       return clubService.createClub(club);
    }
    @GetMapping("/get")
    public @ResponseBody ClubResDTO getClub(@RequestParam Long clubID) {
       return clubService.retClub(clubID);
    }
}

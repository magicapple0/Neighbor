package com.example.neighbor.controllers;

import com.example.neighbor.services.AnnouncementService;
import com.example.neighbor.models.Announcement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AnnouncementController {

    private final AnnouncementService announcementService;

    @Autowired
    public AnnouncementController(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }

    @GetMapping(value = "/annoucements")
    @ResponseBody
    public List<Announcement> getAllAnnouncements(){
        return announcementService.getAllAnnouncements();
    }
}

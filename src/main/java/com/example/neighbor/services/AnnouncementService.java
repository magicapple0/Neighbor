package com.example.neighbor.services;

import com.example.neighbor.models.Announcement;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AnnouncementService {
    public List<Announcement> announcements = new ArrayList<>();

    public AnnouncementService(){

    }

    public List<Announcement> getAllAnnouncements(){
        return announcements;
    }
}

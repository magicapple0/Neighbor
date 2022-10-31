package com.example.neighbor.announcenemt;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AnnouncementService {
    public List<Announcement> announcements = new ArrayList<>();

    public AnnouncementService(){
        announcements.add(new Announcement(1L));
    }

    public List<Announcement> getAllAnnouncements(){
        return announcements;
    }
}

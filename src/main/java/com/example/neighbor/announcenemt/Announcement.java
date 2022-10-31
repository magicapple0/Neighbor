package com.example.neighbor.announcenemt;

import com.example.neighbor.Address;
import com.example.neighbor.Image;

import java.util.*;

public class Announcement {

    private long id;
    private long authorID;
    private List<Image> images = new ArrayList<>();
    //???
    private int price;
    private String description;
    private Address address;
    private boolean isActive = true;

    public Announcement(long authorID){
        this.authorID = authorID;
    }

    public void setId(long id) { this.id = id; }
    public void setImages(List<Image> images) { this.images = images; }
    public void addImage(Image image) { images.add(image); }
    public void setPrice(int price) { this.price = price; }
    public void setAddress(Address address) { this.address = address; }
    public void setDescription(String description) { this.description = description; }
    public void setActive(boolean active) { isActive = active; }

    public long getId(){ return id; }
    public long getAuthorID(){ return authorID; }
    public List<Image> getImages(){ return images; }
    public int getPrice(){ return price; }
    public String getDescription(){ return description; }
    public Address getAddress(){ return address; }
    public boolean isActive(){ return isActive; }
}

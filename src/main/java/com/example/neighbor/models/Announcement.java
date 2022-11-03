package com.example.neighbor.models;

import com.example.neighbor.Address;
import com.example.neighbor.Image;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Announcement {

    private long id;
    private long authorID;
    private List<Image> images = new ArrayList<>();
    private int price;
    private String description;
    private Address address;
    private boolean isActive = true;
}

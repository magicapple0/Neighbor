package com.example.neighbor.dto;

import com.example.neighbor.models.Image;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdDTO {
    private String id;
    private int price;
    private String ownerName;
    private String title;
    private String description;
    private String[] imagesIds;
    private String category;
}

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
    private long id;
    private String ownerName;
    private long[] imagesIds;
    private int price;
    private String title;
    private String description;
    private String category;
}

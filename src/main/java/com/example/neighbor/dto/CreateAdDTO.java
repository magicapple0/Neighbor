package com.example.neighbor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAdDTO {
    private int price;
    private String description;
    private String title;
    private String category;
}

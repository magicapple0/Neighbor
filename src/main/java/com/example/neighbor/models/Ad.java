package com.example.neighbor.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "ads")
@AllArgsConstructor
@NoArgsConstructor
public class Ad {
    @jakarta.persistence.Id
    @GeneratedValue
    private long id;
    @ManyToOne
    @JoinColumn
    private User owner;
    @ManyToMany
    @JoinColumn
    private Image[] images;
    private int price;
    private String title;
    private String description;
    private String category;
}

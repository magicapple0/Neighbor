package com.example.neighbor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFullDTO {
    private String id;
    private String login;
    private String email;
    private String phone;
    private String name;
    private String avatarId;
    private String description;
    private int role;
}

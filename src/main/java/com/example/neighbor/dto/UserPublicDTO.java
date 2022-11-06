package com.example.neighbor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPublicDTO {
    private String name;
    private String id;
    private String avatarId;
}

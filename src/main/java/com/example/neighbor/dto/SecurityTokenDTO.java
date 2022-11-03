package com.example.neighbor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SecurityTokenDTO {
    private String Login;
    private String Role;
    private String AccessToken;
    private LocalDateTime ExpiredAt;
}

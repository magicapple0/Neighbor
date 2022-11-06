package com.example.neighbor.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @jakarta.persistence.Id
    @GeneratedValue
    private long id = 0;
    private String login;
    private String email;
    private String phone;
    private String name;
    @OneToOne
    @JoinColumn
    private Image avatar;
    private String description;
    private String password;
    private Role role;


}

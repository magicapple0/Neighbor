package com.example.neighbor.user;

import com.example.neighbor.user.User;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserService {
    public List<User> users = new ArrayList<>();

    public UserService(){
        users.add(new User("89193990917", "Lera"));
    }

    public List<User> getAllUsers(){
        return users;
    }
}

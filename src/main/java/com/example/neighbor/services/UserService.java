package com.example.neighbor.services;

import com.example.neighbor.models.Role;
import com.example.neighbor.models.User;
import com.example.neighbor.repositories.UserRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getAllUsers() {
        var users = new ArrayList<User>();
        repository.findAll().forEach(users::add);
        return users;
    }
    @Transactional
    public User GetUser(long id) {
        return repository.findById(id);
    }
    @Transactional
    public User GetUser(String login) {
        return repository.findByLogin(login);
    }

    public User UpdateUser(User newUser)
    {
        return repository.save(newUser);
    }

    @Transactional
    public User CreateUser(User user) {
        if (repository.findByLogin(user.getLogin()) != null)
            throw new EntityExistsException("user exists");
        user.setRole(Role.USER);
        return repository.save(user);
    }

}

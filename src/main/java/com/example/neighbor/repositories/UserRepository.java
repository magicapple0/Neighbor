package com.example.neighbor.repositories;

import com.example.neighbor.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

@Repository
@RestResource(exported = false)
public interface UserRepository extends CrudRepository<User, Long> {
    User findById(long Id);
    User findByLogin(String login);
}

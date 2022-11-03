package com.example.neighbor.repositories;

import com.example.neighbor.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findById(long Id);

    User findByLogin(String login);
}

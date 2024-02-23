package com.example.focus_group.services;

import com.example.focus_group.models.user;
import com.example.focus_group.repositories.UserRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepositories userRepositories;

    public UserService(UserRepositories userRepositories) {
        this.userRepositories = userRepositories;
    }

    @Transactional
    public user save(user user) {
        return userRepositories.save(user);
    }

    public List<user> findAll() {
        return userRepositories.findAll();
    }

}

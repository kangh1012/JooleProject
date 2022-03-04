package com.itlize.jooleproject.service.impl;

import com.itlize.jooleproject.entity.User;
import com.itlize.jooleproject.repository.UserRepository;
import com.itlize.jooleproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository repository;

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return repository.findById(username).orElse(null);
    }

    @Override
    public List<User> findByUserType(String userType) {
        return repository.findByUserType(userType);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(User user) {
        repository.delete(user);
    }
}

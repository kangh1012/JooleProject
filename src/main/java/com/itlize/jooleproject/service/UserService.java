package com.itlize.jooleproject.service;

import com.itlize.jooleproject.entity.User;

import java.util.List;

public interface UserService {
    User save(User user);

    User findByUsername(String username);

    List<User> findByUserType(String userType);

    List<User> findAll();

    void delete(User user);
}

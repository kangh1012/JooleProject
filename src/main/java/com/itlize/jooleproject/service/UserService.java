package com.itlize.jooleproject.service;

import com.itlize.jooleproject.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User save(User user);

    User createNewUser(String username, String password) throws Exception;

    User findByUsername(String username);

    List<User> findByUserType(String userType);

    List<User> findAll();

    void delete(User user);
}

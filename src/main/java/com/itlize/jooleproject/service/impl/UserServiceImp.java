package com.itlize.jooleproject.service.impl;

import com.itlize.jooleproject.entity.User;
import com.itlize.jooleproject.repository.UserRepository;
import com.itlize.jooleproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    @Override
    public User createNewUser(String username, String password) throws Exception {
        User finduser = repository.findById(username).orElse(null);
        if (finduser != null){
            throw new Exception("Username already exists!");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user = repository.save(user);
        return user;
    }

    @Override
    public User findByUsername(String username) {
        return repository.findById(username).orElse(null);
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

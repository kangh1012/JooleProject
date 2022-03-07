package com.itlize.jooleproject.controller;

import com.itlize.jooleproject.entity.User;
import com.itlize.jooleproject.service.UserService;
import com.itlize.jooleproject.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Date;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/register")
    public JsonResult<Void> register(User user){
        LocalDateTime date = LocalDateTime.now();
        user.setTimeCreated(date);
        user.setLastModified(date);

        JsonResult<Void> result = new JsonResult<>();
        if (userService.findByUsername(user.getUsername()) != null){
            result.setState(4000);
            result.setMessage("User already exist.");
        }else try{
            userService.save(user);
            result.setState(200);
            result.setMessage("Successfully registered.");
        }catch(Exception e){
            result.setState(4000);
            result.setMessage("Error happens when trying to register");
        }

        return result;
    }


}

package com.itlize.jooleproject.controller;

import com.itlize.jooleproject.entity.User;
import com.itlize.jooleproject.service.MyUserDetailsService;
import com.itlize.jooleproject.service.UserService;
import com.itlize.jooleproject.util.JsonResult;
import com.itlize.jooleproject.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager myauthenticaitonManager;
    @Autowired
    private JwtUtil jwtTokenUtil;
    @Autowired
    private MyUserDetailsService userDetailsService;

    @RequestMapping("/register")
    public ResponseEntity<?> register(@RequestParam("username") String username,
                                      @RequestParam("password") String password){
        User user = null;
        try{
            user = userService.createNewUser(username, password);
        }catch (Exception e){
            return new ResponseEntity<>("Username is taken.", HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping({ "/hello" })
    public String firstPage() {
        return "Hello World";
    }

    @RequestMapping("/login")
    public ResponseEntity<?> login (@RequestBody User user){

        try {
            myauthenticaitonManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            return  new ResponseEntity<>("Has no right to access", HttpStatus.FORBIDDEN);
        }

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(user.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return  new ResponseEntity<>(jwt, HttpStatus.OK);
    }

}

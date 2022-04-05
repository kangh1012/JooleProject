package com.itlize.jooleproject.controller;

import com.itlize.jooleproject.entity.User;
import com.itlize.jooleproject.service.MyUserDetailsService;
import com.itlize.jooleproject.service.UserService;
import com.itlize.jooleproject.util.JsonResult;
import com.itlize.jooleproject.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> map){
        User user;
        try{
            user = userService.createNewUser(map.get("username"), map.get("password"));
        }catch (Exception e){
            return new ResponseEntity<>("Username is taken.", HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping({ "/hello" })
    public String firstPage() {
        return "Hello World";
    }

    @GetMapping("/login")
    public ResponseEntity<?> login (@RequestParam("username") String username,
                                    @RequestParam("password") String password){

        try {
            myauthenticaitonManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
        }
        catch (BadCredentialsException e) {
            return  new ResponseEntity<>("Has no right to access", HttpStatus.FORBIDDEN);
        }

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(username);

        final String jwt = jwtTokenUtil.generateToken(userDetails);
        Map<String, String> res = new HashMap<>();
        res.put("token", jwt);

        return  new ResponseEntity<>(res, HttpStatus.OK);
    }

    @RequestMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        final String authHeader = request.getHeader("Authorization");
        Map<String, String> res = new HashMap<>();

        if(authHeader != null && authHeader.startsWith("Bearer")) {
            String token = authHeader.substring(7);
            if(!jwtTokenUtil.validateToken(token, userDetailsService.loadUserByUsername(jwtTokenUtil.extractUsername(token)))) {
                res.put("response", "Invalid token");
                return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
            }

            long expiration = jwtTokenUtil.extractExpiration(token).getTime();
            redisTemplate.opsForValue()
                    .set(token, "logout", expiration, TimeUnit.MILLISECONDS);

            res.put("response", "Logout successful");

            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        res.put("error", "No such header");

        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }
}

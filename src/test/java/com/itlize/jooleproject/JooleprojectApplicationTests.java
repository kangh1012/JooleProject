package com.itlize.jooleproject;

import com.itlize.jooleproject.entity.User;
import com.itlize.jooleproject.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class JooleprojectApplicationTests {

    @Autowired
    private DataSource ds;

    @Autowired
    private UserService us;

    @Test
    void contextLoads() {
    }
    @Test
    void getConnection() throws SQLException{
        System.out.println("hello " + ds.getConnection());
    }
    @Test
    void userService() throws SQLException{
        try{
        User user = new User();
        user.setUsername("test001");
        user.setPassword("123456");
        us.save(user);
            System.out.println("OK");
        }catch(Exception e){
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

}

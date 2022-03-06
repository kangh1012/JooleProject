package com.itlize.jooleproject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class JooleprojectApplicationTests {

    @Autowired
    private DataSource ds;

    @Test
    void contextLoads() {
    }
    @Test
    void getConnection() throws SQLException{
        System.out.println(ds.getConnection());
    }

}

package com.itlize.jooleproject.service;

import com.itlize.jooleproject.entity.Role;
import com.itlize.jooleproject.entity.User;
import com.itlize.jooleproject.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void saveTest() {
        User original = new User();
        original.setUsername("test1");
        original.setPassword("password1");
        original.setRole(Role.EndUser);

        User saved = userService.save(original);

        Assertions.assertThat(saved).isNotNull();
        Assertions.assertThat(saved.getUsername()).isEqualTo(original.getUsername());
    }

    @Test
    public void findByUsernameTest() {
 //      User original = new User();
 //       original.setUsername("test1");
  //      original.setPassword("password1");
 //       original.setUserType("normal user");

        User actual = userService.findByUsername("test1");

        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual.getUsername()).isEqualTo("test1");
  //      Assertions.assertThat(actual.getPassword()).isEqualTo(original.getPassword());
  //      Assertions.assertThat(actual.getUserType()).isEqualTo(original.getUserType());
    }

    @Test
    public void findAllTest() {
        List<User> allUsers = userService.findAll();

        Assertions.assertThat(allUsers.size()).isEqualTo(3);
    }

    @Test
    public void deleteTest() {
        User deletedUser = userService.findByUsername("testAdmin");

        userService.delete(deletedUser);

        deletedUser = userService.findByUsername("testAdmin");
        Assertions.assertThat(deletedUser).isNull();
    }
}

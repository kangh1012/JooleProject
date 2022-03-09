package com.itlize.jooleproject.service;

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
        original.setUserType("normal user");

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
    public void findByUserTypeTest() {
        User user1 = new User();
        user1.setUsername("testAdmin");
        user1.setPassword("password2");
        user1.setUserType("admin");
        User user2 = new User();
        user2.setUsername("test2");
        user2.setPassword("password3");
        user2.setUserType("normal user");

        userService.save(user1);
        userService.save(user2);

        List<User> normalUsers = userService.findByUserType("normal user");

        Assertions.assertThat(normalUsers.size()).isEqualTo(2);
        Assertions.assertThat(normalUsers.get(0).getUsername()).isEqualTo("test1");
        Assertions.assertThat(normalUsers.get(1).getUsername()).isEqualTo("test2");
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

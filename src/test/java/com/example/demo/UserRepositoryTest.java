package com.example.demo;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTest {
    @Autowired
    private UserRepository repository;

    @Test
    public void testAddNew(){
        User user = new User();
        user.setEmail("hell@gmail.com");
        user.setPassword("ravi");

        repository.save(user);
    }

    @Test
    public void testFindAll(){
        Iterable<User> users = repository.findAll();
        Assertions.assertThat(users).hasSizeGreaterThan(0);

        for (User user : users) {
            System.out.println(user.getEmail());
        }
    }

    @Test
    public void testUpdate() {
        Integer userId = 3;
        Optional<User> optionalUser = repository.findById(userId);

        User user = optionalUser.get();
        user.setPassword("glekeubeukeul");

        repository.save(user);

        User userUpdate = repository.findById(userId).get();

        Assertions.assertThat(userUpdate.getPassword()).isEqualTo("glekeubeukeul");


    }

    @Test
    public void testGet() {
        Integer userId = 3;
        Optional<User> optionalUser = repository.findById(userId);
        Assertions.assertThat(optionalUser).isPresent();

        System.out.println(optionalUser.get());
    }

    @Test
    void testDelete() {
        Integer userId = 5;
        repository.deleteById(userId);

        Optional<User> optionalUser = repository.findById(userId);
        Assertions.assertThat(optionalUser).isNotPresent();
    }
}

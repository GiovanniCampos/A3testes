package com.nelioalves.workshopmongo.repository;

import com.nelioalves.workshopmongo.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;


@DataMongoTest
@ExtendWith(SpringExtension.class)
@ComponentScan(basePackages = "com.nelioalves.workshopmongo.repository")
public class UserRepositoryTestInt {
	
    @Autowired
    private UserRepository userRepository;


    @Test
    public void testSaveUser() {
        User user = new User("1", "John Doe", "john.doe@example.com");

        User savedUser = userRepository.save(user);

        assertThat(savedUser.getId()).isNotNull();
        assertThat(savedUser.getName()).isEqualTo(user.getName());
        assertThat(savedUser.getEmail()).isEqualTo(user.getEmail());
    }
}


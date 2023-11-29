package com.nelioalves.workshopmongo.services;

import com.nelioalves.workshopmongo.domain.User;
import com.nelioalves.workshopmongo.dto.UserDTO;
import com.nelioalves.workshopmongo.repository.UserRepository;
import com.nelioalves.workshopmongo.services.exception.ObjectNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
@ExtendWith(SpringExtension.class)
@ComponentScan(basePackages = "com.nelioalves.workshopmongo.services")
public class UserServiceTestInt {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;
    
    @BeforeEach
    public void cleanup() {
        userRepository.deleteAll();
    }

    @Test
    public void testFindAll() {
        userRepository.save(new User("1", "joao", "joao@example.com"));
        userRepository.save(new User("2", "pedro", "pedro.doe@example.com"));

        List<User> users = userService.findAll();

        assertNotNull(users);
        assertEquals(2, users.size());
    }

    @Test
    public void testFindById() {
        User user = new User("1", "joao", "joao@example.com");
        userRepository.save(user);

        User foundUser = userService.findById("1");

        assertNotNull(foundUser);
        assertEquals("joao", foundUser.getName());
    }

    @Test
    public void testInsert() {
        UserDTO userDTO = new UserDTO("3", "Carlos", "Carlos@example.com");

        User insertedUser = userService.insert(userDTO);

        assertNotNull(insertedUser);
        assertEquals("3", insertedUser.getId());
        assertEquals("Carlos", insertedUser.getName());
        assertEquals("Carlos@example.com", insertedUser.getEmail());
    }

    @Test
    public void testDelete() {
        User user = new User("4", "joao", "joao@example.com");
        userRepository.save(user);

        userService.delete("4");

        assertThrows(ObjectNotFoundException.class, () -> userService.findById("4"));
    }

    @Test
    public void testUpdate() {
       

        User user = new User(null, "joao", "joao@example.com");
        userRepository.save(user);

        user.setName("Updated Name");
        user.setEmail("updated@example.com");
        User updatedUser = userService.update(user);

        assertEquals("Updated Name", updatedUser.getName());
        assertEquals("updated@example.com", updatedUser.getEmail());
    }
}

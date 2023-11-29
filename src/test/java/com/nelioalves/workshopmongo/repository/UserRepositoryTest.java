package com.nelioalves.workshopmongo.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.nelioalves.workshopmongo.domain.User;
import com.nelioalves.workshopmongo.services.UserService;
@ExtendWith(SpringExtension.class)
@DataMongoTest
public class UserRepositoryTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void testFindAllUsers() {
        User user1 = new User("1", "John Doe", "john@example.com");
        User user2 = new User("2", "Jane Doe", "jane@example.com");
        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        List<User> result = userRepository.findAll();

        assertEquals(2, result.size());
    }

    @Test
    public void testFindUserById() {
        User user = new User("1", "John Doe", "john@example.com");
        when(userRepository.findById("1")).thenReturn(Optional.of(user));

        Optional<User> result = userRepository.findById("1");

        assertTrue(result.isPresent());
        assertEquals("John Doe", result.get().getName());
    }

    @Test
    public void testSaveUser() {
        User userToSave = new User("3", "Alice", "alice@example.com");
        when(userRepository.save(any(User.class))).thenReturn(userToSave);

        User savedUser = userRepository.save(userToSave);

        assertEquals("Alice", savedUser.getName());
        verify(userRepository, times(1)).save(any(User.class));
    }

}
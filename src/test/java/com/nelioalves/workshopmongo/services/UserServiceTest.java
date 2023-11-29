package com.nelioalves.workshopmongo.services;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.nelioalves.workshopmongo.domain.User;
import com.nelioalves.workshopmongo.dto.UserDTO;
import com.nelioalves.workshopmongo.repository.UserRepository;
import com.nelioalves.workshopmongo.services.exception.ObjectNotFoundException;

public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAll() {
        
        when(userRepository.findAll()).thenReturn(Arrays.asList(new User(), new User()));

        
        List<User> result = userService.findAll();

        
        assertEquals(2, result.size());
    }

    @Test
    public void testFindById() {        
        when(userRepository.findById(any())).thenReturn(Optional.of(new User("1", "John Doe", "john.doe@example.com")));

        User result = userService.findById("1");

        assertNotNull(result);
        assertEquals("1", result.getId());
        assertEquals("John Doe", result.getName());
        assertEquals("john.doe@example.com", result.getEmail());
    }

    @Test
    public void testInsert() {
        when(userRepository.save(any(User.class))).thenReturn(new User("1", "John Doe", "john.doe@example.com"));

        UserDTO userDTO = new UserDTO("1", "John Doe", "john.doe@example.com");
        User result = userService.insert(userDTO);

        assertNotNull(result);
        assertEquals("1", result.getId());
        assertEquals("John Doe", result.getName());
        assertEquals("john.doe@example.com", result.getEmail());
    }


    @Test
    public void testDelete() {
        when(userRepository.findById(any())).thenReturn(Optional.of(new User()));

        try {
            userService.delete("1");
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }

        verify(userRepository, times(1)).deleteById("1");
    }


    @Test
    public void testUpdate() {
        when(userRepository.findById(any())).thenReturn(Optional.of(new User()));
        when(userRepository.save(any())).thenReturn(new User("1", "John Doe", "john.doe@example.com"));

        UserDTO userDTO = new UserDTO("1", "John Doe", "john.doe@example.com");

        User user = userService.fromDTO(userDTO);
        User result = userService.update(user);

        assertNotNull(result);
        assertEquals("1", result.getId());
        assertEquals("John Doe", result.getName());
        assertEquals("john.doe@example.com", result.getEmail());
    }


    @Test
    public void testFromDTO() {
        User result = userService.fromDTO(new UserDTO("1", "John Doe", "john.doe@example.com"));

        assertNotNull(result);
        assertEquals("1", result.getId());
        assertEquals("John Doe", result.getName());
        assertEquals("john.doe@example.com", result.getEmail());
    }

    @Test
    public void testFindById_ObjectNotFoundException() {
        when(userRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> userService.findById("1"));
    }
}


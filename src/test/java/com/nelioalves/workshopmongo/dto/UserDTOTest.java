package com.nelioalves.workshopmongo.dto;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.nelioalves.workshopmongo.domain.User;

public class UserDTOTest {

    @Test
    public void testConstructorFromUser() {
        User user = mock(User.class);

        when(user.getId()).thenReturn("1");
        when(user.getName()).thenReturn("John Doe");
        when(user.getEmail()).thenReturn("john.doe@example.com");

        UserDTO userDTO = new UserDTO(user);

        assertEquals("1", userDTO.getId());
        assertEquals("John Doe", userDTO.getName());
        assertEquals("john.doe@example.com", userDTO.getEmail());
    }

    @Test
    public void testParameterizedConstructor() {
        UserDTO userDTO = new UserDTO("1", "John Doe", "john.doe@example.com");

        assertEquals("1", userDTO.getId());
        assertEquals("John Doe", userDTO.getName());
        assertEquals("john.doe@example.com", userDTO.getEmail());
    }
}

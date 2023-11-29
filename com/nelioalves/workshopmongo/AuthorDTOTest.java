package com.nelioalves.workshopmongo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.nelioalves.workshopmongo.domain.User;
import com.nelioalves.workshopmongo.dto.AuthorDTO;

public class AuthorDTOTest {

    @Test
    public void testConstructorWithUser() {
        User user = new User("1", "John Doe", "john.doe@example.com");
        AuthorDTO authorDTO = new AuthorDTO(user);

        assertEquals("1", authorDTO.getId());
        assertEquals("John Doe", authorDTO.getName());
    }

    @Test
    public void testDefaultConstructor() {
        AuthorDTO authorDTO = new AuthorDTO();

        assertNull(authorDTO.getId());
        assertNull(authorDTO.getName());
    }

    @Test
    public void testSetters() {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId("2");
        authorDTO.setName("Jane Doe");

        assertEquals("2", authorDTO.getId());
        assertEquals("Jane Doe", authorDTO.getName());
    }
}	
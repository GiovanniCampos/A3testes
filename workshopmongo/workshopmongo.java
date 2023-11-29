package com.nelioalves.workshopmongo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.nelioalves.workshopmongo.domain.User;
import com.nelioalves.workshopmongo.dto.UserDTO;

public class UserDTOTest {

    @Test
    public void testConstructorFromUser() {
        // Crie um mock de User
        User user = mock(User.class);

        // Configure o comportamento esperado para o mock
        when(user.getId()).thenReturn("1");
        when(user.getName()).thenReturn("John Doe");
        when(user.getEmail()).thenReturn("john.doe@example.com");

        // Crie um UserDTO usando o construtor que recebe um User
        UserDTO userDTO = new UserDTO(user);

        // Verifique se os atributos de UserDTO foram preenchidos corretamente
        assertEquals("1", userDTO.getId());
        assertEquals("John Doe", userDTO.getName());
        assertEquals("john.doe@example.com", userDTO.getEmail());
    }

    @Test
    public void testParameterizedConstructor() {
        // Crie um UserDTO usando o construtor que recebe parâmetros
        UserDTO userDTO = new UserDTO("1", "John Doe", "john.doe@example.com");

        // Verifique se os atributos de UserDTO foram preenchidos corretamente
        assertEquals("1", userDTO.getId());
        assertEquals("John Doe", userDTO.getName());
        assertEquals("john.doe@example.com", userDTO.getEmail());
    }
}
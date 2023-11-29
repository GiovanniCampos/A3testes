package com.nelioalves.workshopmongo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.Test;

import com.nelioalves.workshopmongo.dto.AuthorDTO;
import com.nelioalves.workshopmongo.dto.CommentDTO;

public class CommentDTOTest {

    @Test
    public void testConstructor() {
        // Crie um mock de Date
        Date mockDate = mock(Date.class);

        // Configure o comportamento esperado para o mock de Date
        when(mockDate.getTime()).thenReturn(123456789L);

        // Crie um mock de AuthorDTO
        AuthorDTO mockAuthorDTO = mock(AuthorDTO.class);

        // Configure o comportamento esperado para o mock de AuthorDTO
        when(mockAuthorDTO.getName()).thenReturn("Test Author");

        // Crie um CommentDTO usando o construtor que recebe parâmetros
        CommentDTO commentDTO = new CommentDTO("Test Comment", mockDate, mockAuthorDTO);

        // Verifique se os atributos de CommentDTO foram preenchidos corretamente
        assertEquals("Test Comment", commentDTO.getText());
        assertEquals(mockDate, commentDTO.getDate());
        assertEquals(mockAuthorDTO, commentDTO.getAuthor());
    }
@Test
    public void testGettersAndSetters() {
        // Crie um CommentDTO
        CommentDTO commentDTO = new CommentDTO();

        // Configure valores usando setters
        commentDTO.setText("Another Comment");

        // Crie um mock de Date
        Date mockDate = mock(Date.class);

        // Configure o comportamento esperado para o mock de Date
        when(mockDate.getTime()).thenReturn(987654321L);

        // Use o setter para configurar a data
        commentDTO.setDate(mockDate);

        // Crie um mock de AuthorDTO
        AuthorDTO mockAuthorDTO = mock(AuthorDTO.class);

        // Configure o comportamento esperado para o mock de AuthorDTO
        when(mockAuthorDTO.getName()).thenReturn("Another Author");

        // Use o setter para configurar o autor
        commentDTO.setAuthor(mockAuthorDTO);

        // Verifique se os valores foram configurados corretamente usando getters
        assertEquals("Another Comment", commentDTO.getText());
        assertEquals(mockDate, commentDTO.getDate());
        assertEquals(mockAuthorDTO, commentDTO.getAuthor());
    }
}
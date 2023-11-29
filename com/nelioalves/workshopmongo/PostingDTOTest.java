package com.nelioalves.workshopmongo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.Test;

import com.nelioalves.workshopmongo.dto.PostingDTO;

public class PostingDTOTest {

    @Test
    public void testConstructor() {
        // Crie um mock de Date
        Date mockDate = mock(Date.class);

        // Configure o comportamento esperado para o mock de Date
        when(mockDate.getTime()).thenReturn(123456789L);

        // Crie um PostingDTO usando o construtor que recebe parâmetros
        PostingDTO postingDTO = new PostingDTO(1L, "Test Title", "Test Body", "author123", mockDate);

        // Verifique se os atributos de PostingDTO foram preenchidos corretamente
        assertEquals(Long.valueOf(1), postingDTO.getId());
        assertEquals("Test Title", postingDTO.getTitle());
        assertEquals("Test Body", postingDTO.getBody());
        assertEquals("author123", postingDTO.getAuthorId());
        assertEquals(mockDate, postingDTO.getDate());
    }
@Test
    public void testGettersAndSetters() {
        // Crie um PostingDTO
        PostingDTO postingDTO = new PostingDTO();

        // Configure valores usando setters
        postingDTO.setId(2L);
        postingDTO.setTitle("Another Title");
        postingDTO.setBody("Another Body");
        postingDTO.setAuthorId("author456");

        // Crie um mock de Date
        Date mockDate = mock(Date.class);

        // Configure o comportamento esperado para o mock de Date
        when(mockDate.getTime()).thenReturn(987654321L);

        // Use o setter para configurar a data
        postingDTO.setDate(mockDate);

        // Verifique se os valores foram configurados corretamente usando getters
        assertEquals(Long.valueOf(2), postingDTO.getId());
        assertEquals("Another Title", postingDTO.getTitle());
        assertEquals("Another Body", postingDTO.getBody());
        assertEquals("author456", postingDTO.getAuthorId());
        assertEquals(mockDate, postingDTO.getDate());
    }
}
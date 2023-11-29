package com.nelioalves.workshopmongo.dto;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.Test;

public class CommentDTOTest {

    @Test
    public void testConstructor() {
        Date mockDate = mock(Date.class);

        when(mockDate.getTime()).thenReturn(123456789L);

        AuthorDTO mockAuthorDTO = mock(AuthorDTO.class);

        when(mockAuthorDTO.getName()).thenReturn("Test Author");

        CommentDTO commentDTO = new CommentDTO("Test Comment", mockDate, mockAuthorDTO);

        assertEquals("Test Comment", commentDTO.getText());
        assertEquals(mockDate, commentDTO.getDate());
        assertEquals(mockAuthorDTO, commentDTO.getAuthor());
    }

    @Test
    public void testGettersAndSetters() {
        CommentDTO commentDTO = new CommentDTO();

        commentDTO.setText("Another Comment");

        Date mockDate = mock(Date.class);

        when(mockDate.getTime()).thenReturn(987654321L);

        commentDTO.setDate(mockDate);

        AuthorDTO mockAuthorDTO = mock(AuthorDTO.class);

        when(mockAuthorDTO.getName()).thenReturn("Another Author");

        commentDTO.setAuthor(mockAuthorDTO);

        assertEquals("Another Comment", commentDTO.getText());
        assertEquals(mockDate, commentDTO.getDate());
        assertEquals(mockAuthorDTO, commentDTO.getAuthor());
    }
}


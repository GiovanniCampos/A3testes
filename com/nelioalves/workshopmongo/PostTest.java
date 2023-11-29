package com.nelioalves.workshopmongo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.nelioalves.workshopmongo.domain.Post;
import com.nelioalves.workshopmongo.dto.AuthorDTO;
import com.nelioalves.workshopmongo.dto.CommentDTO;

public class PostTest {

    @Test
    public void testGetterAndSetter() {
        
        AuthorDTO authorDTO = mock(AuthorDTO.class);
        CommentDTO commentDTO = mock(CommentDTO.class);

        
        List<CommentDTO> comments = new ArrayList<>();
        comments.add(commentDTO);

       
        Post post = new Post("1", new Date(), "Test Title", "Test Body", authorDTO, comments);
        
        

        
        assertEquals("1", post.getId());
        assertEquals(new Date(), post.getDate());
        assertEquals("Test Title", post.getTitle());
        assertEquals("Test Body", post.getBody());
        assertEquals(authorDTO, post.getAuthor());
        assertEquals(comments, post.getComments());
    }
}

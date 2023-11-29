package com.nelioalves.workshopmongo.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.nelioalves.workshopmongo.dto.AuthorDTO;

@RunWith(MockitoJUnitRunner.class)
public class PostTest {

    @InjectMocks
    private Post post;

    @Mock
    private AuthorDTO authorDTO;

    @Before
    public void setUp() {
        post = new Post("1", new Date(), "Title", "Body", authorDTO);
    }

    @Test
    public void testGettersAndSetters() {
        assertEquals("1", post.getId());
        assertEquals("Title", post.getTitle());
        assertEquals("Body", post.getBody());
        assertEquals(authorDTO, post.getAuthor());

        Date newDate = new Date();
        post.setId("2");
        post.setTitle("New Title");
        post.setBody("New Body");
        post.setAuthor(mock(AuthorDTO.class));
        post.setDate(newDate);

        assertEquals("2", post.getId());
        assertEquals("New Title", post.getTitle());
        assertEquals("New Body", post.getBody());
        assertNotEquals(authorDTO, post.getAuthor());
        assertEquals(newDate, post.getDate());
    }

    @Test
    public void testEqualsAndHashCode() {
        Post post1 = new Post("1", new Date(), "Title", "Body", authorDTO);
        Post post2 = new Post("1", new Date(), "Title", "Body", authorDTO);

        assertEquals(post1, post2);
        assertEquals(post1.hashCode(), post2.hashCode());

        post2.setId("2");

        assertNotEquals(post1, post2);
        assertNotEquals(post1.hashCode(), post2.hashCode());
    }
}

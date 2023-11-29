package com.nelioalves.workshopmongo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.Date;

import org.junit.Test;

import com.nelioalves.workshopmongo.domain.Posting;
import com.nelioalves.workshopmongo.domain.User;

public class PostingTest {

    @Test
    public void testGetterAndSetter() {
        User author = mock(User.class);
        Date date = new Date();

        Posting posting = new Posting();
        posting.setId(1L);
        posting.setTitle("Test Title");
        posting.setBody("Test Body");
        posting.setAuthor(author);
        posting.setDate(date);

        assertEquals(Long.valueOf(1L), posting.getId());
        assertEquals("Test Title", posting.getTitle());
        assertEquals("Test Body", posting.getBody());
        assertEquals(author, posting.getAuthor());
        assertEquals(date, posting.getDate());
    }

    @Test
    public void testToString() {
        User author = mock(User.class);
        Date date = new Date();

        Posting posting = new Posting();
        posting.setId(1L);
        posting.setTitle("Test Title");
        posting.setBody("Test Body");
        posting.setAuthor(author);
        posting.setDate(date);

        String expectedToString = "Post{id=1, title=Test Title, body=Test Body, author=" + author + " , date=" + date.toString() + '}';
        assertEquals(expectedToString, posting.toString());
    }
}

package com.nelioalves.workshopmongo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.nelioalves.workshopmongo.domain.Post;
import com.nelioalves.workshopmongo.domain.User;

@RunWith(MockitoJUnitRunner.class)
public class UserTest {

    @Mock
    private List<Post> mockPosts;

    @InjectMocks
    private User user;

    @Before
    public void setup() {
    	  user = new User("1", "John Doe", "john.doe@example.com");
          user.setPosts(mockPosts);
      }

    @Test
    public void testGetId() {
        assertEquals("1", user.getId());
    }

    @Test
    public void testGetName() {
        assertEquals("John Doe", user.getName());
    }

    @Test
    public void testGetEmail() {
        assertEquals("john.doe@example.com", user.getEmail());
    }

    @Test
    public void testGetPosts() {
        assertEquals(mockPosts, user.getPosts());
    }

    @Test
    public void testSetPosts() {
        List<Post> newPosts = new ArrayList<>();
        user.setPosts(newPosts);
        assertEquals(newPosts, user.getPosts());
    }

    @Test
    public void testEquals() {
        User otherUser = new User("1", "John Doe", "john.doe@example.com");
        assertTrue(user.equals(otherUser));
    }

    @Test
    public void testHashCode() {
        User otherUser = new User("1", "John Doe", "john.doe@example.com");
        assertEquals(user.hashCode(), otherUser.hashCode());
    }


}

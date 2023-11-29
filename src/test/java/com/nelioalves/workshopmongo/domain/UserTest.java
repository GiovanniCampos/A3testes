package com.nelioalves.workshopmongo.domain;

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
    public void testGetUser() {
    	assertEquals("1", user.getId());
        assertEquals("John Doe", user.getName());
        assertEquals("john.doe@example.com", user.getEmail());
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

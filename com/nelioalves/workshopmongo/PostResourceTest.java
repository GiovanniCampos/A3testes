package com.nelioalves.workshopmongo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.nelioalves.workshopmongo.domain.Post;
import com.nelioalves.workshopmongo.services.PostService;
import com.nelioalves.workshopmongo.resources.PostResource;

@RunWith(MockitoJUnitRunner.class)
public class PostResourceTest {

    @Mock
    private PostService postService;

    @InjectMocks
    private PostResource postResource;

    @Before
    public void setUp() {
        
    }

    @Test
    public void testFindById() {
        
        Post post = new Post("1", new Date(), "Test Title", "Test Body", null);

        
        when(postService.findById("1")).thenReturn(post);

        
        ResponseEntity<Post> responseEntity = postResource.findById("1");

        
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(post, responseEntity.getBody());
    }

    @Test
    public void testFindByTitle() {
        
        List<Post> posts = new ArrayList<>();
        posts.add(new Post("1", new Date(), "Test Title 1", "Test Body 1", null));
        posts.add(new Post("2", new Date(), "Test Title 2", "Test Body 2", null));

        
        when(postService.findByTitle("Test Title")).thenReturn(posts);

        
        ResponseEntity<List<Post>> responseEntity = postResource.findByTitle("Test Title");

        
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(posts, responseEntity.getBody());
    }

    
}


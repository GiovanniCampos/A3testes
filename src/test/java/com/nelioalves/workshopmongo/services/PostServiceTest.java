package com.nelioalves.workshopmongo.services;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.nelioalves.workshopmongo.domain.Post;
import com.nelioalves.workshopmongo.repository.PostRepository;


public class PostServiceTest {

    @InjectMocks
    private PostService postService;

    @Mock
    private PostRepository postRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindById() throws ParseException {
        Post mockPost = createMockPost("1", "Title", "Body");
        when(postRepository.findById(eq("1"))).thenReturn(Optional.of(mockPost));

        Post result = postService.findById("1");

        assertNotNull(result);
        assertEquals("1", result.getId());
        assertEquals("Title", result.getTitle());
        assertEquals("Body", result.getBody());
    }

    private Post createMockPost(String id, String title, String body) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        return new Post(id, sdf.parse("23/03/2018"), title, body,null);
    }
    
    @Test
    public void testFindByTitle() throws ParseException {
        when(postRepository.searchTitle(any())).thenReturn(createSamplePostList());

        List<Post> result = postService.findByTitle("Title");

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Title1", result.get(0).getTitle());
        assertEquals("Title2", result.get(1).getTitle());
    }

    
    private List<Post> createSamplePostList() throws ParseException {
        List<Post> posts = new ArrayList<>();
        posts.add(createMockPost("1", "Title1", "Body1"));
        posts.add(createMockPost("2", "Title2", "Body2"));
        return posts;
        
    }
    @Test
    public void testFullSearch() {
        String searchText = "example";
        Date minDate = new Date(0L);
        Date maxDate = new Date(1L);

        Post post1 = new Post("1", new Date(), "Title1", "Body1", null);
        Post post2 = new Post("2", new Date(), "Title2", "Body2", null);

        List<Post> mockPosts = new ArrayList<>();
        mockPosts.add(post1);
        mockPosts.add(post2);

        when(postRepository.fullSearch(eq(searchText), any(Date.class), any(Date.class))).thenReturn(mockPosts);

        List<Post> result = postService.fullSearch(searchText, minDate, maxDate);

        assertEquals(mockPosts, result);
    }
}


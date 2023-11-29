package com.nelioalves.workshopmongo.resources;

import com.nelioalves.workshopmongo.domain.Post;
import com.nelioalves.workshopmongo.services.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class PostResourceTest {

    private MockMvc mockMvc;

    @Mock
    private PostService postService;

    @InjectMocks
    private PostResource postResource;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(postResource).build();
    }

    @Test
    public void testFindById() throws Exception {
        String postId = "123";
        Post mockPost = new Post(postId, null, "Title", "Body", null);

        when(postService.findById(postId)).thenReturn(mockPost);

        mockMvc.perform(get("/posts/{id}", postId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)) 
                .andExpect(jsonPath("$.id").value(postId))
                .andExpect(jsonPath("$.title").value("Title"))
                .andExpect(jsonPath("$.body").value("Body"));
    }


    @Test
    public void testFindByTitle() throws Exception {
        String searchText = "SearchText";
        List<Post> mockPosts = new ArrayList<>();
        mockPosts.add(new Post("1", null, "Title1", "Body1", null));
        mockPosts.add(new Post("2", null, "Title2", "Body2", null));

        when(postService.findByTitle(anyString())).thenReturn(mockPosts);

        mockMvc.perform(get("/posts/titlesearch").param("text", searchText))
                .andExpect(status().isOk())
                .andExpect(content().json("[{ \"title\": \"Title1\" }, { \"title\": \"Title2\" }]"));
    }


    @Test
    public void testFullSearch() throws Exception {
        String searchText = "SearchText";
        String minDate = "2022-01-01";
        String maxDate = "2023-01-01";

        List<Post> mockPosts = new ArrayList<>();
        mockPosts.add(new Post("1", null, "Title1", "Body1", null));
        mockPosts.add(new Post("2", null, "Title2", "Body2", null));

        when(postService.fullSearch(anyString(), any(Date.class), any(Date.class))).thenReturn(mockPosts);

        mockMvc.perform(get("/posts/fullsearch")
                .param("text", searchText)
                .param("minDate", minDate)
                .param("maxDate", maxDate))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].title").value("Title1"))
                .andExpect(jsonPath("$[1].title").value("Title2"));
    }

}
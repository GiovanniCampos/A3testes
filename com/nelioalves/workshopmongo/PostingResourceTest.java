package com.nelioalves.workshopmongo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.nelioalves.workshopmongo.domain.Posting;
import com.nelioalves.workshopmongo.resources.PostingResource;
import com.nelioalves.workshopmongo.services.PostingService;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class PostingResourceTest {

    @Mock
    private PostingService postingService;

    @InjectMocks
    private PostingResource postingResource;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllPostings() {
      
        List<Posting> postings = new ArrayList<>();
        postings.add(new Posting());
        postings.add(new Posting());

        
        when(postingService.getAllPostings()).thenReturn(postings);

        
        ResponseEntity<List<Posting>> responseEntity = postingResource.getAllPostings();

        
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(postings, responseEntity.getBody());
    }

    @Test
    public void testGetPostingById() {
        
        Posting posting = new Posting();

        
        when(postingService.findById("1")).thenReturn(posting);

        
        ResponseEntity<Posting> responseEntity = postingResource.getPostingById("1");

        
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(posting, responseEntity.getBody());
    }

    @Test
    public void testCreatePosting() {
        
        Posting postings = new Posting();

        
        when(postingService.savePosting(postings)).thenReturn(postings);

        
        ResponseEntity<Posting> responseEntity = postingResource.createPosting(postings);

        
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(postings, responseEntity.getBody());
    }
}

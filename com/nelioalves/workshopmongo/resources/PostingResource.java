package com.nelioalves.workshopmongo.resources;

import com.nelioalves.workshopmongo.domain.Posting;
import com.nelioalves.workshopmongo.services.PostingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/postings")
public class PostingResource {

    private final PostingService postingService;

    public PostingResource(PostingService postingService) {
        this.postingService = postingService;
    }

    @GetMapping
    public ResponseEntity<List<Posting>> getAllPostings() {
        List<Posting> postings = postingService.getAllPostings();
        return new ResponseEntity<>(postings, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Posting> getPostingById(@PathVariable String id) {
    	Object post = (postingService.findById(id));
		return null;
    	}


    @PostMapping
    public ResponseEntity<Posting> createPosting(@RequestBody Posting posting) {
        Posting createdPosting = postingService.savePosting(posting);
        return new ResponseEntity<>(createdPosting, HttpStatus.CREATED);
    }

    // Adicione outros métodos conforme necessário
}
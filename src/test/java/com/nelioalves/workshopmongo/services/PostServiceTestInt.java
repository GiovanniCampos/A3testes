package com.nelioalves.workshopmongo.services;

import com.nelioalves.workshopmongo.domain.Post;
import com.nelioalves.workshopmongo.repository.PostRepository;
import com.nelioalves.workshopmongo.services.exception.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataMongoTest
@ExtendWith(SpringExtension.class)
@ComponentScan(basePackages = "com.nelioalves.workshopmongo.services")
public class PostServiceTestInt {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostService postService;

    @BeforeEach
    public void setUp() {
        postRepository.deleteAll();
    }

    @Test
    public void testFindById() {
        Post post = new Post(null, new Date(), "Test Title", "Test Body", null);
        post = postRepository.save(post);

        Post foundPost = postService.findById(post.getId());

        assertEquals(post, foundPost);
    }

    @Test
    public void testFindByTitle() {
        Post post1 = new Post(null, new Date(), "Java Title", "Java Body", null);
        Post post2 = new Post(null, new Date(), "Spring Title", "Spring Body", null);

        postRepository.save(post1);
        postRepository.save(post2);

        List<Post> foundPosts = postService.findByTitle("Title");

        assertEquals(2, foundPosts.size());
    }
    
    @Test
    public void testFullSearch() throws ParseException {
        postRepository.deleteAll();

            // Save test data to the database
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date date1 = sdf.parse("21/03/2018");
            Date date2 = sdf.parse("22/03/2018");

            Post post1 = new Post(null, date1, "Macdonalds", "Teste", null);
            Post post2 = new Post(null, date2, "Parque", "Teste", null);

            postRepository.save(post1);
            postRepository.save(post2);

            // Perform the service call
            List<Post> foundPosts = postService.fullSearch("Test", date1, date2);

            // Verify the result
            assertEquals(2, foundPosts.size());
            assertEquals(post1, foundPosts.get(0));
    }

    @Test
    public void testFindByIdNotFound() {
        assertThrows(ObjectNotFoundException.class, () -> postService.findById("nonexistent-id"));
    }
}

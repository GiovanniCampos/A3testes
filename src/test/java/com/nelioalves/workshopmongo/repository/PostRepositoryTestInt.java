package com.nelioalves.workshopmongo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.nelioalves.workshopmongo.domain.Post;

@DataMongoTest
@ExtendWith(SpringExtension.class)
@ComponentScan(basePackages = "com.nelioalves.workshopmongo.repository")
public class PostRepositoryTestInt {

    @Autowired
    private PostRepository postRepository;
    @BeforeEach
    public void setUp() {
        postRepository.deleteAll();
    }


    @Test
    public void testSearchTitle() {
        // Given
        Post post1 = new Post(null, null, "Title 1", "Body 1", null);
        Post post2 = new Post(null, null, "Title 2", "Body 2", null);
        postRepository.saveAll(Arrays.asList(post1, post2));

        // When
        List<Post> result = postRepository.searchTitle("title");

        // Then
        assertThat(result).hasSize(2);
        assertThat(result).extracting("title").contains("Title 1", "Title 2");
    }

    @Test
    public void testFindByTitleContainingIgnoreCase() {
        // Given
        Post post1 = new Post(null, null, "Spring Boot", "Body 1", null);
        Post post2 = new Post(null, null, "Spring Data", "Body 2", null);
        postRepository.saveAll(Arrays.asList(post1, post2));

        // When
        List<Post> result = postRepository.findByTitleContainingIgnoreCase("spring");

        // Then
        assertThat(result).hasSize(2);
        assertThat(result).extracting("title").contains("Spring Boot", "Spring Data");
    }

    @Test
    public void testFullSearch() {
        Date now = new Date();
        Post post1 = new Post(null, now, "Title 1", "Body 1", null);
        Post post2 = new Post(null, now, "Title 2", "Body 2", null);
        postRepository.saveAll(Arrays.asList(post1, post2));

        // Quando
        List<Post> result = postRepository.fullSearch("title", now, now);

        // Então
        assertThat(result).hasSize(2);
        assertThat(result).extracting("title").contains("Title 1", "Title 2");
    }

}


package com.nelioalves.workshopmongo.repository;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.nelioalves.workshopmongo.domain.Post;
import com.nelioalves.workshopmongo.domain.User;
import com.nelioalves.workshopmongo.dto.AuthorDTO;
@ExtendWith(SpringExtension.class)
@DataMongoTest
public class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @MockBean
    private PostRepository mockPostRepository;

    @Test
    public void testSearchTitle() throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        User maria = new User(null, "Maria Brown", "maria@gmail.com");

        List<Post> mockPosts = Arrays.asList (
                new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria)),
                new Post(null, sdf.parse("23/03/2018"), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(maria)));

        when(mockPostRepository.searchTitle("Title")).thenReturn(mockPosts);

        List<Post> actualPosts = postRepository.searchTitle("Title");

        assertEquals(mockPosts.size(), actualPosts.size());

        assertAll(() -> assertPostDetails(mockPosts.get(0), actualPosts.get(0)),
                  () -> assertPostDetails(mockPosts.get(1), actualPosts.get(1)));
    }

    public void assertPostDetails(Post expected, Post actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getDate(), actual.getDate());
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getBody(), actual.getBody());
        assertEquals(expected.getAuthor().getName(), actual.getAuthor().getName());

    }
}
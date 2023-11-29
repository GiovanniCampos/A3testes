package com.nelioalves.workshopmongo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.nelioalves.workshopmongo.domain.Post;
import com.nelioalves.workshopmongo.repository.PostRepository;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @MockBean
    private PostRepository mockPostRepository;

    @Test
    public void testSearchTitle() {
        // Crie uma lista de posts fictícia para simular a resposta do banco de dados
        List<Post> mockPosts = Arrays.asList(
                new Post("1", null, "Title 1", "Body 1", new Date()),
                new Post("2", null, "Title 2", "Body 2", new Date()));

        // Configure o comportamento do mock quando o método de pesquisa for chamado
        when(mockPostRepository.searchTitle("Title")).thenReturn(mockPosts);

        // Execute a operação real no repositório
        List<Post> actualPosts = postRepository.searchTitle("Title");

        // Verifique se os resultados correspondem aos resultados do mock
        assertEquals(mockPosts.size(), actualPosts.size());
    }

    @Test
    public void testFindByTitleContainingIgnoreCase() {
        // Crie uma lista de posts fictícia para simular a resposta do banco de dados
        List<Post> mockPosts = Arrays.asList(
                new Post("1", null, "Title 1", "Body 1", new Date()),
                new Post("2", null, "Title 2", "Body 2", new Date()));

        // Configure o comportamento do mock quando o método findByTitleContainingIgnoreCase for chamado
        when(mockPostRepository.findByTitleContainingIgnoreCase("Title")).thenReturn(mockPosts);

        // Execute a operação real no repositório
        List<Post> actualPosts = postRepository.findByTitleContainingIgnoreCase("Title");

        // Verifique se os resultados correspondem aos resultados do mock
        assertEquals(mockPosts.size(), actualPosts.size());
    }

    // Adicione outros testes para o método fullSearch, se necessário
}
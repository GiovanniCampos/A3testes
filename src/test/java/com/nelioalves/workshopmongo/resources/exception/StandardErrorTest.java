package com.nelioalves.workshopmongo.resources.exception;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StandardErrorTest {

    @Test
    public void testGettersAndSetters() {
        // Criação de instância de StandardError para o teste
        Long timestamp = System.currentTimeMillis();
        Integer status = 404;
        String error = "Not Found";
        String message = "Resource not found";
        String path = "/api/resource/123";

        StandardError standardError = new StandardError(timestamp, status, error, message, path);

        // Testando os métodos getters
        assertEquals(timestamp, standardError.getTimestamp());
        assertEquals(status, standardError.getStatus());
        assertEquals(error, standardError.getError());
        assertEquals(message, standardError.getMessage());
        assertEquals(path, standardError.getPath());

        // Modificando valores com métodos setters
        Long newTimestamp = System.currentTimeMillis();
        Integer newStatus = 500;
        String newError = "Internal Server Error";
        String newMessage = "Unexpected error occurred";
        String newPath = "/api/error";

        standardError.setTimestamp(newTimestamp);
        standardError.setStatus(newStatus);
        standardError.setError(newError);
        standardError.setMessage(newMessage);
        standardError.setPath(newPath);

        // Testando se os valores foram modificados corretamente
        assertEquals(newTimestamp, standardError.getTimestamp());
        assertEquals(newStatus, standardError.getStatus());
        assertEquals(newError, standardError.getError());
        assertEquals(newMessage, standardError.getMessage());
        assertEquals(newPath, standardError.getPath());
    }
}


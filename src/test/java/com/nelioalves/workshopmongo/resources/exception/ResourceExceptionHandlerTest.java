package com.nelioalves.workshopmongo.resources.exception;


import com.nelioalves.workshopmongo.services.exception.ObjectNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ResourceExceptionHandlerTest {

    @Test
    public void testObjectNotFound() {
        ResourceExceptionHandler exceptionHandler = new ResourceExceptionHandler();

        // Mocking the HttpServletRequest
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn("/example");

        // Creating an ObjectNotFoundException
        ObjectNotFoundException exception = new ObjectNotFoundException("Object not found");

        // Handling the exception
        ResponseEntity<StandardError> responseEntity = exceptionHandler.objectNotFound(exception, request);

        // Verifying the response
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("/example", responseEntity.getBody().getPath());
        // Add more assertions based on your StandardError structure
    }
}


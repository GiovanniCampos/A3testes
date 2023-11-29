package com.nelioalves.workshopmongo.resources;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nelioalves.workshopmongo.domain.User;
import com.nelioalves.workshopmongo.dto.UserDTO;
import com.nelioalves.workshopmongo.services.UserService;

@RunWith(MockitoJUnitRunner.class)
public class UserResourceTest {

    @Mock
    private UserService userService;

    @Mock
    private HttpServletRequest request;

    @InjectMocks
    private UserResource userResource;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    @Test
    public void testFindAll() {
        List<User> users = new ArrayList<>();
        users.add(new User("1", "John Doe", "john.doe@example.com"));
        users.add(new User("2", "Jane Doe", "jane.doe@example.com"));


        when(userService.findAll()).thenReturn(users);
ResponseEntity<List<UserDTO>> responseEntity = userResource.findAll();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(users.size(), responseEntity.getBody().size());
    }

    private URI getUserResourceUri(String userId) {
        return ServletUriComponentsBuilder.fromUriString("/users/{id}")
                .buildAndExpand(userId)
                .toUri();
    }

    @Test
    public void testFindById() {
        User user = new User("1", "John Doe", "john.doe@example.com");

        when(userService.findById("1")).thenReturn(user);

        ResponseEntity<UserDTO> responseEntity = userResource.findById("1");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(user.getId(), responseEntity.getBody().getId());
        assertEquals(user.getName(), responseEntity.getBody().getName());
        assertEquals(user.getEmail(), responseEntity.getBody().getEmail());
    }


    @Test
    public void testInsert() {
        UserDTO userDTO = new UserDTO("1", "John Doe", "john.doe@example.com");

        when(userService.fromDTO(any(UserDTO.class))).thenReturn(new User());
        when(userService.insert(any(User.class))).thenReturn(new User("1", "John Doe", "john.doe@example.com"));
        
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        userResource.setService(userService);
        ResponseEntity<Void> responseEntity = userResource.insert(userDTO);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        String expectedUri = getUserResourceUri("1").getPath().replace("/users", "");
        String actualUri = responseEntity.getHeaders().getLocation().getPath().replace("/users", "");
        assertEquals(expectedUri, actualUri);
    }
    
    @Test
    public void testDelete() {
        String userId = "123";
        doNothing().when(userService).delete(userId);

        ResponseEntity<Void> responseEntity = userResource.delete(userId);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    public void testUpdate() {
        String userId = "123";
        UserDTO userDTO = new UserDTO("1","John Doe", "john@example.com");
        User user = new User(userId, userDTO.getName(), userDTO.getEmail());

        when(userService.fromDTO(userDTO)).thenReturn(user);
        when(userService.update(any(User.class))).thenReturn(user);

        ResponseEntity<Void> responseEntity = userResource.update(userDTO, userId);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }
}

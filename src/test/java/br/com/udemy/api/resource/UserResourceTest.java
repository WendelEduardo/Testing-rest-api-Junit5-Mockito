package br.com.udemy.api.resource;

import br.com.udemy.api.domain.Users;
import br.com.udemy.api.domain.dto.UserDTO;
import br.com.udemy.api.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserResourceTest {

    @InjectMocks
    private UserResource resource;

    @Mock
    private ModelMapper mapper;

    @Mock
    private UserService service;

    private Users users;
    private UserDTO userDTO;

    public static final Integer ID      = 1;
    public static final String NOME     = "Wendel";
    public static final String EMAIL    = "wendel@hotmail.com";
    public static final String PASSWORD = "1234";
    public static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado!";
    public static final int INDEX = 0;

    private void startUsers(){
        users = new Users(ID, NOME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NOME, EMAIL, PASSWORD);
    }

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        startUsers();
    }

    @Test
    void whenFindByIdTheReturnAnUserDto() {
        when(service.findById(anyInt())).thenReturn(users);
        when(mapper.map(any(), any())).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = resource.findById(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(UserDTO.class, response.getBody().getClass());

        assertEquals(ID, response.getBody().getId());
        assertEquals(NOME, response.getBody().getName());
        assertEquals(EMAIL, response.getBody().getEmail());
        assertEquals(PASSWORD, response.getBody().getPassword());
    }

    @Test
    void listar() {
        when(service.findAll()).thenReturn(List.of(users, users));
        when(mapper.map(any(), any())).thenReturn(userDTO);

        ResponseEntity<List<UserDTO>> lista = resource.listar();

        assertNotNull(lista);
        assertEquals(ResponseEntity.class, lista.getClass());

        assertEquals(ID, lista.getBody().get(INDEX).getId());
        assertEquals(NOME, lista.getBody().get(INDEX).getName());
        assertEquals(EMAIL, lista.getBody().get(INDEX).getEmail());
        assertEquals(PASSWORD, lista.getBody().get(INDEX).getPassword());

        assertEquals(HttpStatus.OK, lista.getStatusCode());
    }

    @Test
    void create() {

        when(service.create(userDTO)).thenReturn(users);
        when(mapper.map(any(), any())).thenReturn(userDTO);

        ResponseEntity<UserDTO> usuarioCriado = resource.create(userDTO);

        assertNotNull(usuarioCriado);

        assertEquals(ResponseEntity.class, usuarioCriado.getClass());
        assertEquals(ID, usuarioCriado.getBody().getId());
        assertEquals(NOME, usuarioCriado.getBody().getName());
        assertEquals(EMAIL, usuarioCriado.getBody().getEmail());
        assertEquals(PASSWORD, usuarioCriado.getBody().getPassword());

        assertEquals(HttpStatus.CREATED, usuarioCriado.getStatusCode());
        assertNotNull(usuarioCriado.getHeaders().get("Location"));
    }

    @Test
    void update() {
        when(service.update(userDTO)).thenReturn(users);
        when(mapper.map(any(), any())).thenReturn(userDTO);

        ResponseEntity<UserDTO> updateUser = resource.update(userDTO, ID);

        assertNotNull(updateUser);

        assertEquals(ResponseEntity.class, updateUser.getClass());

        assertEquals(ResponseEntity.class, updateUser.getClass());
        assertEquals(ID, updateUser.getBody().getId());
        assertEquals(NOME, updateUser.getBody().getName());
        assertEquals(EMAIL, updateUser.getBody().getEmail());
        assertEquals(PASSWORD, updateUser.getBody().getPassword());

        assertEquals(HttpStatus.OK, updateUser.getStatusCode());
    }

    @Test
    void delete() {

    }
}
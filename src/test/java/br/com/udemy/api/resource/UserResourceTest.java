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

        assertEquals(ID, lista.getBody().get(0).getId());
        assertEquals(NOME, lista.getBody().get(0).getName());
        assertEquals(EMAIL, lista.getBody().get(0).getEmail());
        assertEquals(PASSWORD, lista.getBody().get(0).getPassword());
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}
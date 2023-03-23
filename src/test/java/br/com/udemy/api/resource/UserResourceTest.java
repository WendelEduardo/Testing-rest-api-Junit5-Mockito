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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

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
    void findById() {
    }

    @Test
    void listar() {
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
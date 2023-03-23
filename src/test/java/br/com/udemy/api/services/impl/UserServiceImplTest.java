package br.com.udemy.api.services.impl;

import br.com.udemy.api.domain.Users;
import br.com.udemy.api.domain.dto.UserDTO;
import br.com.udemy.api.repositories.UserRepository;
import br.com.udemy.api.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplTest {

    public static final Integer ID      = 1;
    public static final String NOME     = "Wendel";
    public static final String EMAIL    = "wendel@hotmail.com";
    public static final String PASSWORD = "1234";
    public static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado!";
    public static final int INDEX = 0;
    @InjectMocks
    private UserServiceImpl service;
    @Mock
    private UserRepository repository;
    @Mock
    private ModelMapper mapper;

    private Users users;
    private UserDTO userDTO;
    private Optional<Users> optionalUsers;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUsers();
    }


    @Test
    void whenFindByIdThenReturnAnUserInstance() {
        // Test Repository
        when(repository.findById(anyInt())).thenReturn(optionalUsers);

        //Test Service
        Users response = service.findById(ID);

        // Asserções
        assertNotNull(response);
        assertEquals(Users.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NOME, response.getName());
        assertEquals(EMAIL, response.getEmail());
    }

    @Test
    void whenFindByIdThenReturnAnObjectNotFoundException(){
        when(repository.findById(anyInt())).thenThrow(new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO));

        try{
            service.findById(ID);
        }catch(Exception ex){
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(OBJETO_NAO_ENCONTRADO, ex.getMessage());
        }
    }

    @Test
    void whenFindAllThenReturnAnListOfInstanceUsers() {

        //Test Repository
        when(repository.findAll()).thenReturn(List.of(users));

        // Test Service
        List<Users> usuarios = service.findAll();

        //Asserções
        assertNotNull(usuarios);
        assertEquals(1, usuarios.size());
        assertEquals(Users.class, usuarios.get(INDEX).getClass());

        assertEquals(ID, usuarios.get(INDEX).getId());
        assertEquals(NOME, usuarios.get(INDEX).getName());
        assertEquals(EMAIL, usuarios.get(INDEX).getEmail());
        assertEquals(PASSWORD, usuarios.get(INDEX).getPassword());
    }

    @Test
    void whenCreateThenReturnAnSucess() {

        // Test repository
        when(repository.save(ArgumentMatchers.any())).thenReturn(users);

        // Test Service
        Users usuarioCriado = service.create(userDTO);

        assertNotNull(usuarioCriado);
        assertEquals(Users.class, usuarioCriado.getClass());
        assertEquals(ID, usuarioCriado.getId());
        assertEquals(NOME, usuarioCriado.getName());
        assertEquals(EMAIL, usuarioCriado.getEmail());
        assertEquals(PASSWORD, usuarioCriado.getPassword());
    }

    @Test
    void whenCreateThenReturnDataIntegrityViolationException(){

        // Test repository
        when(repository.findByEmail(ArgumentMatchers.anyString())).thenReturn(optionalUsers);

        try{
            optionalUsers.get().setId(2);
            service.create(userDTO);
        }catch (Exception ex){
            assertEquals(DataIntegrityViolationException.class, ex.getClass());

            assertEquals("Email já cadastrado no sistema.", ex.getMessage());
        }
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private void startUsers(){
        users = new Users(ID, NOME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NOME, EMAIL, PASSWORD);
        optionalUsers = Optional.of(new Users(ID, NOME, EMAIL, PASSWORD));
    }
}
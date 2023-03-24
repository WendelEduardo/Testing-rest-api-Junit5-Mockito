package br.com.udemy.api.resource.exceptions;

import br.com.udemy.api.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class ResourceExceptionHandlerTest {

    public static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado!";
    @InjectMocks
    private ResourceExceptionHandler handler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void objectNotFound() {
        ResponseEntity<StandardError> erro = handler.objectNotFound(new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO)
                , new MockHttpServletRequest());

        assertNotNull(erro);
        assertNotNull(erro.getBody());
        assertEquals(HttpStatus.NOT_FOUND, erro.getStatusCode());
        assertEquals(ResponseEntity.class, erro.getClass());
        assertEquals(StandardError.class, erro.getBody().getClass());
        assertEquals(OBJETO_NAO_ENCONTRADO, erro.getBody().getError());
        assertEquals(404, erro.getBody().getStatus());
    }

    @Test
    void dataIntegratyViolationExcecption() {
    }
}
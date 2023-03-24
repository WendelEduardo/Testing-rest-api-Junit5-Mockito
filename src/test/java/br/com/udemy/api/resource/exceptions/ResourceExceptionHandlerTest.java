package br.com.udemy.api.resource.exceptions;

import br.com.udemy.api.services.exceptions.DataIntegratyViolationException;
import br.com.udemy.api.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
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
    public static final String EMAIL_JA_CADASTRADO_NO_SISTEMA = "Email já cadastrado no sistema.";
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

        ResponseEntity<StandardError> erro = handler.dataIntegratyViolationExcecption(new DataIntegratyViolationException(EMAIL_JA_CADASTRADO_NO_SISTEMA),
                new MockHttpServletRequest());

        assertNotNull(erro);
        assertNotNull(erro.getBody());
        assertEquals(ResponseEntity.class, erro.getClass());
        assertEquals(HttpStatus.BAD_REQUEST, erro.getStatusCode());
        assertEquals(StandardError.class, erro.getBody().getClass());
        assertEquals(EMAIL_JA_CADASTRADO_NO_SISTEMA, erro.getBody().getError());
        assertEquals(400, erro.getBody().getStatus());

        assertNotNull(erro.getBody().getTimestamp());
        assertNotNull(erro.getBody().getPath());
    }
}
package com.nisum.exercise.controller;

import com.nisum.exercise.dto.user.ResponseCreatedUsuarioDTO;
import com.nisum.exercise.dto.user.UsuarioDTO;
import com.nisum.exercise.exception.ControllerException;
import com.nisum.exercise.exception.ValidationException;
import com.nisum.exercise.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioControllerTest {

    @InjectMocks
    private UsuarioController target;

    @Mock
    private UsuarioService usuarioService;

    @Test
    void createUsuario_ValidUsuario_ReturnsCreatedResponse() throws ControllerException, ValidationException {
        // Arrange
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        ResponseCreatedUsuarioDTO expectedResponse = new ResponseCreatedUsuarioDTO();

        when(usuarioService.createUser(any())).thenReturn(expectedResponse);

        // Act
        ResponseEntity<ResponseCreatedUsuarioDTO> responseEntity = target.createUsuario(usuarioDTO);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
        verify(usuarioService, times(1)).createUser(eq(usuarioDTO));
    }

}
package com.nisum.exercise.service.impl;

import com.nisum.exercise.dto.user.ResponseCreatedUsuarioDTO;
import com.nisum.exercise.dto.user.TelefonoUserDTO;
import com.nisum.exercise.dto.user.UsuarioDTO;
import com.nisum.exercise.entity.Phone;
import com.nisum.exercise.entity.Usuario;
import com.nisum.exercise.exception.ValidationException;
import com.nisum.exercise.repository.UsuarioRepository;
import com.nisum.exercise.service.UsuarioService;
import com.nisum.exercise.util.ValidatePass;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceImplTest {

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private ValidatePass validatePass;

    @Test
    void createUser_ValidUsuario_ReturnsResponseCreatedUsuarioDTO() throws ValidationException {
        // Arrange
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setEmail("lalalala@mail.com");
        usuarioDTO.setPassword("Hun@ter2");

        TelefonoUserDTO telefonoUserDTO = new TelefonoUserDTO();
        telefonoUserDTO.setNumber("741256");
        telefonoUserDTO.setCityCode("7");
        telefonoUserDTO.setContryCode("57");
        List<TelefonoUserDTO> listado = new ArrayList<>();
        listado.add(telefonoUserDTO);
        usuarioDTO.setPhones(listado);
        Usuario usuario = new Usuario();
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setUsuarioId(33456464L);
        usuario.setCreated(LocalDateTime.now());
        usuario.setLastLogin(LocalDateTime.now());
        usuario.setModified(LocalDateTime.now());
        usuario.setName("name");
        usuario.setPassword("Hun@ter2");
        Phone phone = new Phone();
        phone.setUsuarioId(33456464L);
        phone.setPhoneId(33456464L);
        phone.setNumber("741256");
        phone.setCityCode("7");
        phone.setContryCode("57");
        List<Phone> listPhone = new ArrayList<>();
        listPhone.add(phone);
        usuario.setPhone(listPhone);
        when(usuarioRepository.findByEmail(any())).thenReturn(Collections.emptyList());
        when(usuarioRepository.save(any())).thenReturn(usuario);
        when(validatePass.isValidPassword(any())).thenReturn(true);

        // Act
        ResponseCreatedUsuarioDTO response = usuarioService.createUser(usuarioDTO);

        // Assert
        assertEquals("CREATED", response.getStatus());
        assertNotNull(response.getId());
        assertEquals(usuario.getCreated().toString(), response.getCreated());
        assertEquals(usuario.getLastLogin().toString(), response.getLastLogin());
        assertEquals(usuario.getModified().toString(), response.getModified());
        assertTrue(response.isActive());

        verify(usuarioRepository, times(1)).findByEmail(eq(usuarioDTO.getEmail()));
        verify(usuarioRepository, times(1)).save(any());
    }

    @Test
    void createUser_DuplicateEmail_ReturnsErrorResponse() throws ValidationException {
        // Arrange
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        when(usuarioRepository.findByEmail(any())).thenReturn(Collections.singletonList(new Usuario()));

        // Act
        ResponseCreatedUsuarioDTO response = usuarioService.createUser(usuarioDTO);

        // Assert
        assertEquals("Error", response.getStatus());
        assertEquals("El correo ya registrado", response.getMessage());
        assertNull(response.getId());

        verify(usuarioRepository, times(1)).findByEmail(eq(usuarioDTO.getEmail()));
        verify(usuarioRepository, never()).save(any());
    }

    @Test
    void createUser_InvalidEmailFormat_ReturnsErrorResponse() throws ValidationException {
        // Arrange
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setEmail("@mail.com");
        // Act
        ResponseCreatedUsuarioDTO response = usuarioService.createUser(usuarioDTO);

        // Assert
        assertEquals("Error", response.getStatus());
        assertEquals("Formato de correo no es el correcto", response.getMessage());
        assertNull(response.getId());
    }

}
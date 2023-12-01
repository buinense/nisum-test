package com.nisum.exercise.controller;

import com.nisum.exercise.dto.user.ResponseCreatedUsuarioDTO;
import com.nisum.exercise.dto.user.UsuarioDTO;
import com.nisum.exercise.exception.ControllerException;
import com.nisum.exercise.exception.ValidationException;
import com.nisum.exercise.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/usuarios")
@Slf4j
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping(value = "registro", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ResponseCreatedUsuarioDTO> createUsuario(@RequestBody UsuarioDTO usuario) throws ControllerException, ValidationException {

        ResponseCreatedUsuarioDTO response = usuarioService.createUser(usuario);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }
}

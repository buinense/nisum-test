package com.nisum.exercise.service;

import com.nisum.exercise.dto.user.ResponseCreatedUsuarioDTO;
import com.nisum.exercise.dto.user.UsuarioDTO;
import com.nisum.exercise.exception.ValidationException;

public interface UsuarioService {

    ResponseCreatedUsuarioDTO createUser(UsuarioDTO usuarioDto) throws ValidationException;
}

package com.nisum.exercise.dto.user;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UsuarioDTO {

    private String name;
    private String email;
    private String password;

    private List<TelefonoUserDTO> phones;
}

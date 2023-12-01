package com.nisum.exercise.dto.user;

import com.nisum.exercise.dto.ResponseResult;
import com.nisum.exercise.model.Usuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseCreatedUsuarioDTO extends ResponseResult {

    private static final long serialVersionUID = 601278541314143812L;

    private Usuario usuario;
    private String id;
    private String created;
    private String modified;
    private String lastLogin;
    private String token;
    private boolean isActive;

}

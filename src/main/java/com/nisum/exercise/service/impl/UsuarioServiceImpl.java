package com.nisum.exercise.service.impl;


import com.nisum.exercise.dto.user.ResponseCreatedUsuarioDTO;
import com.nisum.exercise.dto.user.UsuarioDTO;
import com.nisum.exercise.entity.Phone;
import com.nisum.exercise.entity.Usuario;
import com.nisum.exercise.exception.ValidationException;
import com.nisum.exercise.repository.UsuarioRepository;
import com.nisum.exercise.service.UsuarioService;
import com.nisum.exercise.util.JwtUtil;
import com.nisum.exercise.util.ValidatePass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

@Slf4j
@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    ValidatePass validatePass;

    @Override
    public ResponseCreatedUsuarioDTO createUser(UsuarioDTO usuarioDto) throws ValidationException {

        Usuario usuario = new Usuario();
        ResponseCreatedUsuarioDTO response = new ResponseCreatedUsuarioDTO();
        if (!usuarioRepository.findByEmail(usuarioDto.getEmail()).isEmpty()) {
            response.setStatus("Error");
            response.setMessage("El correo ya registrado");
            return response;
        }

        if(!patternMatches(usuarioDto.getEmail())){
            response.setStatus("Error");
            response.setMessage("Formato de correo no es el correcto");
            return response;
        }

        if(!validatePass.isValidPassword(usuarioDto.getPassword())){
            response.setStatus("Error");
            response.setMessage("Formato password incorrecto");
            return response;
        }

        usuario.setName(usuarioDto.getName());
        usuario.setEmail(usuarioDto.getEmail());
        usuario.setPassword(usuarioDto.getPassword());
        usuario.setCreated(LocalDateTime.now());
        usuario.setModified(LocalDateTime.now());
        usuario.setLastLogin(LocalDateTime.now());

        usuarioDto.getPhones().forEach(item  -> {
            Phone phone = new Phone();
            phone.setNumber(item.getNumber());
            phone.setContryCode(item.getContryCode());
            phone.setCityCode(item.getCityCode());
            usuario.getPhone().add(phone);
        });

        Usuario newUsuario = usuarioRepository.save(usuario);


        response.setId(newUsuario.getUsuarioId().toString());
        response.setStatus("CREATED");
        response.setCreated(newUsuario.getCreated().toString());
        response.setLastLogin(newUsuario.getLastLogin().toString());
        response.setModified(newUsuario.getModified().toString());
        response.setToken(jwtUtil.createToken(newUsuario.getName(), "admin"));
        response.setActive(true);

        return response;

    }

    public static boolean patternMatches(String email){
        String regexPattern = "^(.+)@(.+)$";
        return Pattern.compile(regexPattern)
                .matcher(email)
                .matches();
    }


}

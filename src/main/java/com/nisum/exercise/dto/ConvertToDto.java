package com.nisum.exercise.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nisum.exercise.dto.user.ResponseCreatedUsuarioDTO;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@Slf4j
public class ConvertToDto {

    /*private final ModelMapper modelMapper;*/

    private static final String STATUS_ERROR = "Error";

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
    private String dateMinLimit;


    public static ResponseCreatedUsuarioDTO mapErrorResponseCreatedUsuario(List<String> errors, String errorMessage){
        ResponseCreatedUsuarioDTO response = new ResponseCreatedUsuarioDTO();
        response.setStatus(STATUS_ERROR);
        response.setMessage(errorMessage);
        response.setErrorsValidation(errors);
        return response;
    }
}

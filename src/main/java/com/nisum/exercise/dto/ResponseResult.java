package com.nisum.exercise.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResponseResult {
    private String status;
    private String message;
    private List<String> errorsValidation;
}

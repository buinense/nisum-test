package com.nisum.exercise.exception;

import java.util.List;

public class ValidationException extends Exception{

    private List<String> errors;


    public ValidationException(String message, List<String> errors) {
        super(message);
        this.errors = errors;
    }

    public ValidationException(String message, Throwable cause, List<String> errors) {
        super(message, cause);
        this.errors = errors;
    }

    public List<String> getErrors() {
        return this.errors;
    }
}

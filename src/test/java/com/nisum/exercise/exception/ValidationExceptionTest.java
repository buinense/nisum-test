package com.nisum.exercise.exception;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ValidationExceptionTest {

    @Test
    void constructorWithMessageAndErrors_ShouldSetMessageAndErrors() {
        // Arrange
        String message = "Validation failed";
        List<String> errors = Arrays.asList("Error 1", "Error 2");

        // Act
        ValidationException validationException = new ValidationException(message, errors);

        // Assert
        assertEquals(message, validationException.getMessage());
        assertEquals(errors, validationException.getErrors());
    }

    @Test
    void constructorWithMessageCauseAndErrors_ShouldSetMessageCauseAndErrors() {
        // Arrange
        String message = "Validation failed";
        Throwable cause = new RuntimeException("Underlying cause");
        List<String> errors = Arrays.asList("Error 1", "Error 2");

        // Act
        ValidationException validationException = new ValidationException(message, cause, errors);

        // Assert
        assertEquals(message, validationException.getMessage());
        assertEquals(cause, validationException.getCause());
        assertEquals(errors, validationException.getErrors());
    }

    @Test
    void getErrors_ShouldReturnErrors() {
        // Arrange
        String message = "Validation failed";
        List<String> errors = Arrays.asList("Error 1", "Error 2");
        ValidationException validationException = new ValidationException(message, errors);

        // Act
        List<String> retrievedErrors = validationException.getErrors();

        // Assert
        assertEquals(errors, retrievedErrors);
    }

}
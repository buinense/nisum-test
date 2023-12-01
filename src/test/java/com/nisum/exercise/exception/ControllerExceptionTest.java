package com.nisum.exercise.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerExceptionTest {

    @Test
    void constructorWithException_ShouldSetCause() {
        // Arrange
        Exception underlyingException = new RuntimeException("Underlying exception");

        // Act
        ControllerException controllerException = new ControllerException(underlyingException);

        // Assert
        assertEquals(underlyingException, controllerException.getCause());
    }

}
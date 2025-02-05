package org.github.jandin88.mygl.controller.exception;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ControllerError {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> ResponseAlreadyRegistered(DataIntegrityViolationException e, HttpServletRequest request){

        String detailedMessage = "Email or username already registered.";
        HttpStatus status= HttpStatus.CONFLICT;
        StandardError error= new StandardError(Instant.now(),status.value()
                ,detailedMessage, request.getRequestURI());

        return ResponseEntity.status(status).body(error);

    }
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<StandardError> noSuchElementException(NoSuchElementException e, HttpServletRequest request){

        HttpStatus status= HttpStatus.CONFLICT;
        StandardError error= new StandardError(Instant.now(),status.value()
                ,e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(error);

    }




}

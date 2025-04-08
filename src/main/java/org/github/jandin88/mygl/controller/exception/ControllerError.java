package org.github.jandin88.mygl.controller.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.NoSuchElementException;

@RestControllerAdvice

public class ControllerError {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> ResponseAlreadyRegistered( HttpServletRequest request){

        String detailedMessage = "Email or username already registered.";
        HttpStatus status= HttpStatus.CONFLICT;
        StandardError error= new StandardError(Instant.now(),status.value()
                ,detailedMessage, request.getRequestURI());

        return ResponseEntity.status(status).body(error);

    }
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<StandardError> noSuchElementException(NoSuchElementException e, HttpServletRequest request){

        HttpStatus status= HttpStatus.NOT_FOUND;
        StandardError error= new StandardError(Instant.now(),status.value()
                ,e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(error);

    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<StandardError> entidadeNotFound(EntityNotFoundException e, HttpServletRequest request){

        HttpStatus status= HttpStatus.NOT_FOUND;
        StandardError error= new StandardError(Instant.now(),status.value()
              ,e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(error);

    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<StandardError> entidadeNotFound(HttpServletRequest request){

        HttpStatus status= HttpStatus.BAD_REQUEST;
        String message= "the data sent for update is incorrect";
        StandardError error= new StandardError(Instant.now(),status.value()
              ,message, request.getRequestURI());

        return ResponseEntity.status(status).body(error);

    }


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<StandardError> illegalArgument(IllegalArgumentException e, HttpServletRequest request){

        HttpStatus status= HttpStatus.BAD_REQUEST;
        StandardError error= new StandardError(Instant.now(),status.value()
              ,e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(error);

    }


    @ExceptionHandler(CustomException.class)
    public ResponseEntity<StandardError> tokenJwtNotValid(CustomException e, HttpServletRequest request){
        HttpStatus status= HttpStatus.BAD_REQUEST;
        StandardError error= new StandardError(Instant.now(),status.value()
              ,e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(error);

    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<StandardError> unauthorizedClass(AuthorizationDeniedException e, HttpServletRequest request){
        HttpStatus status= HttpStatus.BAD_REQUEST;
        StandardError error= new StandardError(Instant.now(),status.value()
              ,e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(error);

    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<StandardError> unauthorizedClass(UsernameNotFoundException e, HttpServletRequest request){
        HttpStatus status= HttpStatus.NOT_FOUND;
        StandardError error= new StandardError(Instant.now(),status.value()
              ,e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(error);

    }
}

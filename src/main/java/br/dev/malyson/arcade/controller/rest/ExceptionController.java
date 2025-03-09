package br.dev.malyson.arcade.controller.rest;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.dev.malyson.arcade.dto.exceptions.ErrorDTO;

import jakarta.servlet.ServletException;

@RestControllerAdvice
public class ExceptionController {
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> exceptionHandler(Exception ex){
        return new ResponseEntity<>(new ErrorDTO(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ServletException.class)
    public ResponseEntity<ErrorDTO> exceptionHandler(ServletException ex){
        return new ResponseEntity<>(new ErrorDTO(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorDTO> exceptionHandler(IOException ex){
        return new ResponseEntity<>(new ErrorDTO(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorDTO> exceptionHandler(AuthenticationException ex){
        return new ResponseEntity<>(new ErrorDTO(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorDTO> exceptionHandler(BindException ex){
        return new ResponseEntity<>(new ErrorDTO(ex.getFieldErrors()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorDTO> exceptionHandler(UsernameNotFoundException ex){
        return new ResponseEntity<>(new ErrorDTO(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

}

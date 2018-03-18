package com.example.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.NoResultException;
import java.io.IOException;

@Slf4j
@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    private void logError(Exception e) {
        log.error("Error caught by Global Exception Handler", e);
    }

    @ExceptionHandler(value = NoResultException.class)
    public ResponseEntity<String> defaultErrorHandler(Exception e) throws Exception {
        logError(e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {NullPointerException.class, IllegalAccessException.class, UnsupportedOperationException.class, IOException.class})
    public ResponseEntity<String> nullPointerErrorHandler(Exception e) throws Exception {
        logError(e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

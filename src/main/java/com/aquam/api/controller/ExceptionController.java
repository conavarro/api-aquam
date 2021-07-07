package com.aquam.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ExceptionController implements ErrorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionController.class);

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> noSuchElementExceptionHandlerMethod(NoSuchElementException exception){
        Map<String, Object> response = new HashMap<>();
        response.put("code", -1);
        response.put("message", exception.getMessage());
        LOGGER.error(exception.getMessage());
        return ResponseEntity.ok(response);
    }

    @RequestMapping("/error")
    public ResponseEntity<?> whiteLabelHandlerMethod(Exception e){
        Map<String, Object> response = new HashMap<>();
        response.put("code", -1);
        response.put("message", "Error en la ruta solicitada");
        LOGGER.error(e.getMessage());
        LOGGER.error("Mapeo del error");
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> genericExceptionHandlerMethod(Exception exception){
        Map<String, Object> response = new HashMap<>();
        response.put("code", -1);
        response.put("message", exception.getMessage());
        LOGGER.error(exception.getMessage());
        return ResponseEntity.ok(response);
    }

    @Deprecated
    @Override
    public String getErrorPath() {
        return null;
    }
}

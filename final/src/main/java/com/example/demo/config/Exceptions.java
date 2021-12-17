/*package com.example.demo.DTO.ApiError;

import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;

 @ControllerAdvice
 public class Exceptions extends InvalidDataAccessApiUsageException {

    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers,
           HttpStatus status,
            WebRequest request) {
        ApiError error = new ApiError();
         error.setStatus(status);
         error.setMensaje(ex.getMessage());
       error.setCantidadDeErrores(ex.getErrorCount());

    }
 }
 */
package com.java.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorApi> handleException(UserNotFoundException ex) {
        log.error("User Not Found {}", ex.getMessage());

        ErrorApi errorApi = new ErrorApi();
        errorApi.setLocalDateTime(LocalDateTime.now());
        errorApi.setMessage(ex.getMessage());
        errorApi.setError("User Not Found Error.");
        errorApi.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());

        return new ResponseEntity<>(errorApi, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorApi> handleException(Exception ex) {
        log.error("Internal Server Error {}", ex.getMessage());

        ErrorApi errorApi = new ErrorApi();
        errorApi.setLocalDateTime(LocalDateTime.now());
        errorApi.setMessage(ex.getMessage());
        errorApi.setError("Somethings wants wrong.");
        errorApi.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());

        return new ResponseEntity<>(errorApi, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorApi> handleException(MethodArgumentNotValidException ex) {

        String error = ex.getBindingResult().getFieldErrors().stream()
                .map(obj -> obj.getField() + ":" + obj.getDefaultMessage())
                .collect(Collectors.joining(","));

        log.error("Validation Error {}", error);
        ErrorApi errorApi = new ErrorApi();
        errorApi.setLocalDateTime(LocalDateTime.now());
        errorApi.setMessage(error);
        errorApi.setError("Validation Error");
        errorApi.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());

        return new ResponseEntity<>(errorApi, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorApi> handleException(HttpMessageNotReadableException ex) {
        log.error("HttpMessageNotReadableException Error {}", ex.getMessage());

        ErrorApi errorApi = new ErrorApi();
        errorApi.setLocalDateTime(LocalDateTime.now());
        errorApi.setMessage(ex.getMessage());
        errorApi.setError("Malformed JSON Data.");
        errorApi.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());

        return new ResponseEntity<>(errorApi, HttpStatus.BAD_REQUEST);
    }
}

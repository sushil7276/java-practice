package com.example.springdto.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.core.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorApi> handleUserNotFoundException(UserNotFoundException ex) {
        log.warn("User not found exception: {}", ex.getMessage());
        return build(HttpStatus.NOT_FOUND, "User Not Found Error.", ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorApi> handleException(MethodArgumentNotValidException ex) {
        String error = ex.getBindingResult().getFieldErrors().stream()
                .map(obj -> obj.getField() + ":" + obj.getDefaultMessage())
                .collect(Collectors.joining(","));
        log.warn("MethodArgumentNotValidException: {}", error);
        return build(HttpStatus.BAD_REQUEST, "Validation Error.", error);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorApi> handleException(HttpMessageNotReadableException ex) {
        log.warn("HttpMessageNotReadableException: {}", ex.getMessage());
        return build(HttpStatus.BAD_REQUEST, "Malformed JSON Data.", "Request body is missing or malformed");
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<ErrorApi> handleException(HandlerMethodValidationException ex) {
        log.warn("HandlerMethodValidationException: {}", ex.getMessage());
        return build(HttpStatus.BAD_REQUEST, "Validation Error.", "page must be >= 0 and size must be between 1 and 100");
    }

    @ExceptionHandler(InvalidSortException.class)
    public ResponseEntity<ErrorApi> handleException(InvalidSortException ex) {
        log.warn("InvalidSortException: {}", ex.getMessage());
        return build(HttpStatus.BAD_REQUEST, "Invalid Sort Property.", ex.getMessage());
    }

    @ExceptionHandler(PropertyReferenceException.class)
    public ResponseEntity<ErrorApi> handleException(PropertyReferenceException ex) {
        log.warn("PropertyReferenceException: {}", ex.getMessage());
        return build(HttpStatus.BAD_REQUEST, "Invalid Sort Property.", ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorApi> handleException(Exception ex) {
        log.error("Unhandled exception", ex);
        return build(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error.", "Unexpected error occurred");
    }

    private ResponseEntity<ErrorApi> build(HttpStatus status, String error, String message) {
        ErrorApi errorApi = new ErrorApi();
        errorApi.setMessage(message);
        errorApi.setStatus(status.value());
        errorApi.setError(error);
        errorApi.setLocalDateTime(LocalDateTime.now());
        return new ResponseEntity<>(errorApi, status);
    }
}

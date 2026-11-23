package com.example.springdto.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorApi {

    private String message;
    private int status;
    private String error;
    private LocalDateTime localDateTime;
}

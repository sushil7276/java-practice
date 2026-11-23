package com.example.resttemplate.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PageResponse<T>(
        List<T> content,
        long totalElements,
        int totalPages,
        int number,
        int size,
        int numberOfElements,
        boolean first,
        boolean last,
        boolean empty) {
}

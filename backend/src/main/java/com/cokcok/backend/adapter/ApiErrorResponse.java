package com.cokcok.backend.adapter;

import lombok.Getter;

@Getter
public class ApiErrorResponse {

    private final String message;

    private ApiErrorResponse(String message) {
        this.message = message;
    }

    public static ApiErrorResponse of(String message) {
        return new ApiErrorResponse(message);
    }
}
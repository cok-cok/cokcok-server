package com.cokcok.backend.domain.exception;

public abstract class NotFoundException extends BusinessException {
    public NotFoundException(String message) {
        super(message);
    }
}
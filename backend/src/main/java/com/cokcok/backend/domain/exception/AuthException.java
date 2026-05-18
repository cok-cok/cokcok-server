package com.cokcok.backend.domain.exception;

public abstract class AuthException extends BusinessException {
    public AuthException(String message) {
        super(message);
    }
}

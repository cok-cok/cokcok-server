package com.cokcok.backend.domain.exception;

public class NicknameDuplicateException extends BusinessException {
    public NicknameDuplicateException() {
        super("이미 사용 중인 닉네임입니다.");
    }
}
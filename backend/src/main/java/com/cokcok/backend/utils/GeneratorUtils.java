package com.cokcok.backend.utils;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.stream.Collectors;

@Component
public class GeneratorUtils {
    private static final int MIN_LENGTH = 1;

    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    public static String generateRandomCode(int length, int startBound, int endBound) {
        if(length < MIN_LENGTH) {
            throw new IllegalArgumentException("랜덤 코드 생성은 최소 1자리 이상 가능합니다.");
        }
        return SECURE_RANDOM.ints(length, startBound, endBound)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining());
    }
}
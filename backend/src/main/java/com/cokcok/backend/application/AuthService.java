package com.cokcok.backend.application;

import com.cokcok.backend.domain.Member;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public Member member() {
        return Member.of(1L, "cokcok@gmail.com", "cokcok", "cokcok");
    }
}
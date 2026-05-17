package com.cokcok.backend.application;

import com.cokcok.backend.adapter.persistence.MemberRepository;
import com.cokcok.backend.application.provided.AuthCommandService;
import com.cokcok.backend.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements AuthCommandService {

    private final MemberRepository memberRepository;

    @Override
    public Member login(String email, String password) {
        return memberRepository.findByEmail(email);
    }
}
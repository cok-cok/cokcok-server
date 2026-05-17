package com.cokcok.backend.application;

import com.cokcok.backend.application.provided.AuthCommandService;
import com.cokcok.backend.application.request.AuthLoginServiceRequest;
import com.cokcok.backend.application.required.MemberRepository;
import com.cokcok.backend.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements AuthCommandService {

    private final MemberRepository memberRepository;

    @Override
    public Member login(AuthLoginServiceRequest request) {
        Member member = memberRepository.findByEmail(request.getEmail());
        member.login(request.getPassword());
        return member;
    }
}
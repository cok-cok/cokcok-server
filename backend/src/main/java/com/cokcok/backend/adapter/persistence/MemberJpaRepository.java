package com.cokcok.backend.adapter.persistence;

import com.cokcok.backend.application.required.MemberRepository;
import com.cokcok.backend.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberJpaRepository implements MemberRepository {

    private final MemberJpaRepositorySupport memberJpaRepositorySupport;

    @Override
    public Member findByEmail(String email) {
        return memberJpaRepositorySupport.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("error message"))
                .toModel();
    }
}

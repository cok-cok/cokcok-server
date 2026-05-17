package com.cokcok.backend.adapter.persistence;

import com.cokcok.backend.application.required.MemberQueryRepository;
import com.cokcok.backend.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberRepository implements MemberQueryRepository {

    private final MemberJpaRepository memberJpaRepository;

    @Override
    public Member findByEmail(String email) {
        return memberJpaRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."))
                .toModel();
    }
}

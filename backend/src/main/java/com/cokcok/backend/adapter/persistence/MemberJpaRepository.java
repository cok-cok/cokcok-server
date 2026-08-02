package com.cokcok.backend.adapter.persistence;

import com.cokcok.backend.application.required.MemberRepository;
import com.cokcok.backend.domain.Member;
import com.cokcok.backend.domain.exception.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberJpaRepository implements MemberRepository {

    private final MemberJpaRepositorySupport memberJpaRepositorySupport;

    @Override
    public Member findByEmail(String email) {
        return memberJpaRepositorySupport.findByEmail(email)
                .orElseThrow(MemberNotFoundException::new)
                .toModel();
    }

    @Override
    public Member save(Member member) {
        return memberJpaRepositorySupport.save(MemberEntity.from(member))
                .toModel();
    }
  
    @Override
    public boolean existByNickname(String nickname) {
        return memberJpaRepositorySupport.existsByNickname(nickname);
    }
}
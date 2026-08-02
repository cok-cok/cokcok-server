package com.cokcok.backend.application;

import com.cokcok.backend.application.provided.MemberQueryService;
import com.cokcok.backend.application.required.MemberRepository;
import com.cokcok.backend.domain.exception.NicknameDuplicateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService implements MemberQueryService {
    private final MemberRepository memberRepository;

    @Override
    public void checkNicknameDuplication(String nickname) {
        boolean isDuplicated = memberRepository.existByNickname(nickname);
        if(isDuplicated) throw new NicknameDuplicateException();
    }
}

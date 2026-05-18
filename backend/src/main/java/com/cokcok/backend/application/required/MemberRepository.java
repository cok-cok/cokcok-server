package com.cokcok.backend.application.required;

import com.cokcok.backend.domain.Member;

public interface MemberRepository {

    Member findByEmail(String email);
}
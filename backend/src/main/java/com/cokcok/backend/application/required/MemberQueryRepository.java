package com.cokcok.backend.application.required;

import com.cokcok.backend.domain.Member;

public interface MemberQueryRepository {

    Member findByEmail(String email);
}
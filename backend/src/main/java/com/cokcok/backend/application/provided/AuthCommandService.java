package com.cokcok.backend.application.provided;

import com.cokcok.backend.domain.Member;

public interface AuthCommandService {

    Member login(String email, String password);
}

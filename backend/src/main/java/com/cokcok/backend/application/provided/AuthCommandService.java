package com.cokcok.backend.application.provided;

import com.cokcok.backend.application.request.AuthLoginServiceRequest;
import com.cokcok.backend.domain.Member;

public interface AuthCommandService {

    Member login(AuthLoginServiceRequest request);
}

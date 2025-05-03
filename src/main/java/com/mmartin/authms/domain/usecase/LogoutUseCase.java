package com.mmartin.authms.domain.usecase;

import com.mmartin.authms.domain.model.Authorization;

public interface LogoutUseCase {

    void logout(final Authorization authorization);
}

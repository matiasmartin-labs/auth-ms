package com.mmartin.authms.domain.usecase;

import com.mmartin.authms.domain.model.Authorization;

public interface ValidateTokenUseCase {

    void validate(final Authorization authorization);
}

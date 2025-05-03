package com.mmartin.authms.domain.usecase;

import com.mmartin.authms.domain.model.User;

public interface SaveUserUseCase {

    void save(final User user);
}

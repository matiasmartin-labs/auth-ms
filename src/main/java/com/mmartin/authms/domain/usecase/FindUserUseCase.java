package com.mmartin.authms.domain.usecase;

import com.mmartin.authms.domain.model.User;
import com.mmartin.authms.domain.model.vo.Username;

import java.util.Optional;

public interface FindUserUseCase {

    Optional<User> findUser(final Username username);
}

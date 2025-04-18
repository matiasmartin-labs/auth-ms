package com.mmartin.authms.domain.repository;

import com.mmartin.authms.domain.model.User;
import com.mmartin.authms.domain.model.vo.Username;

import java.util.Optional;

public interface FindUserRepository {

    Optional<User> findUser(Username username);
}

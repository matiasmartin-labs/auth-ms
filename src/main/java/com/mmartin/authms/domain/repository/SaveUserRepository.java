package com.mmartin.authms.domain.repository;

import com.mmartin.authms.domain.model.User;

public interface SaveUserRepository {

    void save(User user);
}

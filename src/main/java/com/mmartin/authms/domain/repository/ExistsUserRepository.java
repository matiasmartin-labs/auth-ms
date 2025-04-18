package com.mmartin.authms.domain.repository;

import com.mmartin.authms.domain.model.vo.Username;

public interface ExistsUserRepository {

    boolean exists(Username username);
}

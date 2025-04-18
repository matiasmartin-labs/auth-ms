package com.mmartin.authms.domain.usecase;

import com.mmartin.authms.domain.model.vo.Username;

public interface ExistsUserUseCase {

    boolean exists(Username username);
}

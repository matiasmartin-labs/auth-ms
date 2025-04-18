package com.mmartin.authms.domain.model.vo;

import com.mmartin.authms.domain.exception.UsernameEmptyException;
import com.mmartin.authms.domain.exception.ValidationException;
import org.apache.commons.lang3.StringUtils;

public record Username(String value) implements ValueObject<String> {

    public Username {
        if (StringUtils.isBlank(value)) {
            throw new UsernameEmptyException();
        }
    }
}

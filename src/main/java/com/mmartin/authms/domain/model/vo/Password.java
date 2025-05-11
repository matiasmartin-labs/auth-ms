package com.mmartin.authms.domain.model.vo;

import com.mmartin.authms.domain.exception.PasswordEmptyException;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

@EqualsAndHashCode
public class Password implements ValueObject<String> {

    private final String value;

    public Password(String value) {
        if (StringUtils.isBlank(value)) {
            throw new PasswordEmptyException();
        }
        this.value = value;
    }

    @Override
    public String value() {
        return this.value;
    }
}

package com.mmartin.authms.domain.model.vo;

import com.mmartin.authms.domain.provider.PasswordEncoderProvider;

public class EncodedPassword extends Password {

    public EncodedPassword(String value) {
        super(PasswordEncoderProvider.instance().encode(value));
    }

}

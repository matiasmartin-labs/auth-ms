package com.mmartin.authms.infrastructure.presentation.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Builder;

@Builder
@RegisterForReflection
public record ErrorResponse (
        Integer status,
        String code,
        String message
){
}

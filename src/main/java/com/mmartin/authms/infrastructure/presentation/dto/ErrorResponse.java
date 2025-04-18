package com.mmartin.authms.infrastructure.presentation.dto;

import lombok.Builder;

@Builder
public record ErrorResponse (
        Integer status,
        String code,
        String message
){
}

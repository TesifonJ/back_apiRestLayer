package com.sopra.apirestcontroller.common.DTO;

import jakarta.validation.constraints.NotBlank;

public record AuthLoginRequestDto(
    @NotBlank String username,
    @NotBlank String password
) {
    
}

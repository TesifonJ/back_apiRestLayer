package com.sopra.apirestcontroller.common.DTO;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"username", "message", "jwt", "status"})
public record AuthResponseDto(
    String username,
    String message,
    String jwt,
    boolean status
) {
    
}

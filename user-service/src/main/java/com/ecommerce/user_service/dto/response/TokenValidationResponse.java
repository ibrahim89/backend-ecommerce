package com.ecommerce.user_service.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TokenValidationResponse {
    private String message;

    public TokenValidationResponse() {
    }

    public TokenValidationResponse(String message) {
        this.message = message;
    }
}

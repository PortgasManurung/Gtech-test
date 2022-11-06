package com.ecommerce.gtech.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ChangePasswordRequest {
    private String token;
    private String newPassword;
}

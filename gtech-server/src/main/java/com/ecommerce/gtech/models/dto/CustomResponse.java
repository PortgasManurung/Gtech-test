package com.ecommerce.gtech.models.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class CustomResponse<T> {
    public String message;
    public T Data;
}

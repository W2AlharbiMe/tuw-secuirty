package com.example.week06d1security.Api;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponseWithResponse<T> {
    private String message;
    private T data;
}

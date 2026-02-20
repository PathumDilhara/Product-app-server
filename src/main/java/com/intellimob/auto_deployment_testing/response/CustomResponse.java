package com.intellimob.auto_deployment_testing.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomResponse<T> {
    private boolean isSuccess;
    private String message;
    private T data;
}

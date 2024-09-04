package com.example.RecipeProjectBackend.response;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BaseResponse {
    private String status;
    private String errorCode;
    private Object data;
}

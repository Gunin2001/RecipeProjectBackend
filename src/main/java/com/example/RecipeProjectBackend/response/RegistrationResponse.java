package com.example.RecipeProjectBackend.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RegistrationResponse{

    private String token;
}

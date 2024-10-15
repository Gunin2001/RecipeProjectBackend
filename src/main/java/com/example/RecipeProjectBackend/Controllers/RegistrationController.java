package com.example.RecipeProjectBackend.Controllers;


import com.example.RecipeProjectBackend.Request.RegistrationRequest;
import com.example.RecipeProjectBackend.Service.RegistrationService;
import com.example.RecipeProjectBackend.response.BaseResponse;
import com.example.RecipeProjectBackend.response.RegistrationResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class RegistrationController {

    private RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<BaseResponse> register(@RequestBody RegistrationRequest request) throws JsonProcessingException {

        RegistrationResponse registrationResponse = registrationService.register(request);

        return ResponseEntity.ok(BaseResponse.builder().status("").data(registrationResponse).errorCode("").build());
    }

    @GetMapping(path = "confirm")
    public ResponseEntity<BaseResponse> confirm(@RequestParam("token") String token) {

        String confirmationResponse = registrationService.confirmToken(token);
        return ResponseEntity.ok(BaseResponse.builder().errorCode("").data(confirmationResponse).build());
    }
}

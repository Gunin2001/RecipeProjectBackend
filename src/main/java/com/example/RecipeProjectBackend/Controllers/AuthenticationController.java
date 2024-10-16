package com.example.RecipeProjectBackend.Controllers;

import com.example.RecipeProjectBackend.Request.AuthenticationRequest;
import com.example.RecipeProjectBackend.Request.RegisterRequest;
import com.example.RecipeProjectBackend.Service.AuthenticationService;
import com.example.RecipeProjectBackend.response.AuthenticationResponse;
import com.example.RecipeProjectBackend.response.BaseResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth/")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("userSignUp")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.userSignUp(request));
    }
    @PostMapping("userLogin")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.userLogin(request));
    }

    @PostMapping("refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }
    @GetMapping("confirm")
    public ResponseEntity<BaseResponse> confirm(@RequestParam("token") String token) {

        String confirmationResponse = service.confirmToken(token);
        return ResponseEntity.ok(BaseResponse.builder().errorCode("").data(confirmationResponse).build());
    }


}
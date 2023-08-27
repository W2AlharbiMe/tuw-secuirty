package com.example.week06d1security.Controller;

import com.example.week06d1security.Api.ApiResponse;
import com.example.week06d1security.Model.User;
import com.example.week06d1security.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody @Valid User user) {
        authService.register(user);
        return ResponseEntity.ok(new ApiResponse("success."));
    }

    @GetMapping("/logout")
    public ResponseEntity<ApiResponse> logout() {
        return ResponseEntity.ok(new ApiResponse("logged out successfully."));
    }
}

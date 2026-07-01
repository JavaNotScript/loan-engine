package com.loanengine.LoanEngine.auth.internal.controller;

import com.loanengine.LoanEngine.auth.internal.service.AuthService;
import com.loanengine.LoanEngine.auth.internal.util.loginRelated.LoginRequest;
import com.loanengine.LoanEngine.auth.internal.util.loginRelated.LoginResponse;
import com.loanengine.LoanEngine.auth.internal.util.registrationRelated.RegistrationRequest;
import com.loanengine.LoanEngine.auth.internal.util.registrationRelated.RegistrationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponse> register(@RequestBody RegistrationRequest registrationRequest){
        return ResponseEntity.ok(authService.registerUser(registrationRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authService.login(request));
    }
}

package com.loanengine.LoanEngine.auth.internal.controller;

import com.loanengine.LoanEngine.auth.internal.service.AuthService;
import com.loanengine.LoanEngine.auth.internal.util.registrationRelated.RegistrationRequest;
import com.loanengine.LoanEngine.auth.internal.util.registrationRelated.RegistrationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/v1")
@RequiredArgsConstructor
public class UserController {
    private final AuthService userService;

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponse> register(@RequestBody RegistrationRequest registrationRequest){
        return ResponseEntity.ok(userService.registerUser(registrationRequest));
    }
}

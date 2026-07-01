package com.loanengine.LoanEngine.auth.internal.service;

import com.loanengine.LoanEngine.auth.internal.domain.RoleType;
import com.loanengine.LoanEngine.auth.internal.domain.User;
import com.loanengine.LoanEngine.auth.internal.domain.UserRole;
import com.loanengine.LoanEngine.auth.internal.repo.RoleRepository;
import com.loanengine.LoanEngine.auth.internal.repo.UserRepository;
import com.loanengine.LoanEngine.auth.internal.security.JwtTokenGenerator;
import com.loanengine.LoanEngine.auth.internal.security.UserPrincipal;
import com.loanengine.LoanEngine.auth.internal.util.loginRelated.LoginRequest;
import com.loanengine.LoanEngine.auth.internal.util.loginRelated.LoginResponse;
import com.loanengine.LoanEngine.auth.internal.util.registrationRelated.RegistrationRequest;
import com.loanengine.LoanEngine.auth.internal.util.registrationRelated.RegistrationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenGenerator tokenGenerator;
    private final PasswordEncoder passwordEncoder;


    public RegistrationResponse registerUser(RegistrationRequest request) {
        if (userRepository.existsByUsername(request.username())){
            throw new UsernameNotFoundException("User already registered try logging in.");
        }

        User user = new User();
        user.setUsername(request.username());
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setPassword(passwordEncoder.encode(request.password()));

        UserRole role = roleRepository.findByRoleName(RoleType.USER);

        user.setRole(role);

        User createdUser = userRepository.save(user);
        return new RegistrationResponse(
                createdUser.getUsername(),
                createdUser.getFirstName(),
                createdUser.getLastName()
        );
    }

    public LoginResponse login(LoginRequest request){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(),request.password()));

        if (authentication.isAuthenticated()){
            UserPrincipal user =(UserPrincipal) authentication.getPrincipal();

            assert user != null;
            String authToken = tokenGenerator.generateToken(user.getUserID(),user.getUsername(),user.getAuthorities());

            return new LoginResponse(authToken);
        }

        throw new BadCredentialsException("Invalid username/password");
    }
}

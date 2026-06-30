package com.loanengine.LoanEngine.auth.internal.service;

import com.loanengine.LoanEngine.auth.internal.domain.RoleType;
import com.loanengine.LoanEngine.auth.internal.domain.User;
import com.loanengine.LoanEngine.auth.internal.domain.UserRole;
import com.loanengine.LoanEngine.auth.internal.repo.RoleRepository;
import com.loanengine.LoanEngine.auth.internal.repo.UserRepository;
import com.loanengine.LoanEngine.auth.internal.util.registrationRelated.RegistrationRequest;
import com.loanengine.LoanEngine.auth.internal.util.registrationRelated.RegistrationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);


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

        user.setUserRole(role);

        User createdUser = userRepository.save(user);
        return new RegistrationResponse(
                createdUser.getUsername(),
                createdUser.getFirstName(),
                createdUser.getLastName()
        );
    }
}

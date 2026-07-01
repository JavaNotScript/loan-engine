package com.loanengine.LoanEngine.auth.internal.security;

import com.loanengine.LoanEngine.auth.internal.domain.User;
import com.loanengine.LoanEngine.auth.internal.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return new UserPrincipal(
                user.getUserId(),
                user.getUsername(),
                user.getPassword(),
                user.getRole().getRoleName().name()
        );
    }
}

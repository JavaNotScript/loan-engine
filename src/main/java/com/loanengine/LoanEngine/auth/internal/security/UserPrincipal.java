package com.loanengine.LoanEngine.auth.internal.security;

import com.loanengine.LoanEngine.common.security.CurrentUser;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class UserPrincipal implements UserDetails, CurrentUser {
    private final UUID userId;
    private final String username;
    private final String password;
    private final String role;

    public UserPrincipal(UUID userId,String username,String role) {
        this.userId = userId;
        this.username = username;
        this.role = role;
        this.password = "";
    }

    public UserPrincipal(UUID userId, String username, String password, String role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_"+role));
    }

    @Override
    public @Nullable String getPassword() {
        return password;
    }

    @Override
    public UUID getUserID() {
        return userId;
    }

    @Override
    public  String getUsername() {
        return username;
    }

    @Override
    public String getUserRole() {
        return role;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

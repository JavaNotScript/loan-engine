package com.loanengine.LoanEngine.auth.internal.repo;

import com.loanengine.LoanEngine.auth.internal.domain.RoleType;
import com.loanengine.LoanEngine.auth.internal.domain.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<UserRole,Long> {
    UserRole findByRoleName(RoleType roleType);
}

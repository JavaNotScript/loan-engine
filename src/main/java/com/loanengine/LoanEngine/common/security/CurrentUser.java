package com.loanengine.LoanEngine.common.security;

import java.util.UUID;

public interface CurrentUser {
    UUID getUserID();
    String getUsername();
    String getUserRole();
}

package com.loanengine.LoanEngine.auth.internal.util.registrationRelated;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegistrationRequest(
        @NotBlank @Size(min=5) String username,
        @NotBlank @Size(min = 8) String password,
        @NotBlank @Size(min = 3) String firstName,
        @NotBlank @Size(min = 3) String lastName
) {
}

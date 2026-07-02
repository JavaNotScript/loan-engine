package com.loanengine.LoanEngine.calculation.internal.emi;

import java.math.BigDecimal;

public interface EMiCalculator {

    BigDecimal calculate(BigDecimal principalAmount,BigDecimal interestRate,Integer termMonths);
}
//emi = p * r * (1 + r)^n / (1+r)^n - 1
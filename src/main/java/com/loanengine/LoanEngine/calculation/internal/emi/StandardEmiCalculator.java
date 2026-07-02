package com.loanengine.LoanEngine.calculation.internal.emi;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class StandardEmiCalculator implements EMiCalculator {
    private static final MathContext mathContext = new MathContext(20, RoundingMode.HALF_EVEN);


    @Override
    public BigDecimal calculate(BigDecimal principalAmount, BigDecimal annualInterestRate, Integer termMonths) {

        if (annualInterestRate.compareTo(BigDecimal.ZERO) == 0) {
            return principalAmount.divide(
                    BigDecimal.valueOf(termMonths), 2, RoundingMode.HALF_EVEN
            );
        }

        BigDecimal monthlyRate = annualInterestRate
                .divide(BigDecimal.valueOf(100), mathContext)
                                .divide(BigDecimal.valueOf(12), mathContext);
        //(1+r)
        BigDecimal onePlusRate = BigDecimal.ONE.add(monthlyRate);

        //(1+r)^n
        BigDecimal powerN = onePlusRate
                .pow(termMonths,mathContext);

        //p*r*(1+r)^n
        BigDecimal numerator = principalAmount
                .multiply(monthlyRate,mathContext)
                .multiply(powerN,mathContext);

        //(1+r) - 1
        BigDecimal denominator = powerN.subtract(BigDecimal.ONE);


        //emi = p * r * (1 + r)^n / (1+r)^n - 1
        return numerator.divide(
                denominator,
                2,
                RoundingMode.HALF_EVEN
        );
    }

}

package com.loanengine.LoanEngine;

import com.loanengine.LoanEngine.calculation.internal.emi.StandardEmiCalculator;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
@RequiredArgsConstructor
class LoanEngineApplicationTests {
	private final StandardEmiCalculator calculator;

	@Test
	void contextLoads() {
	}

	@Test
	void calculatesMonthlyInstallment(){
		BigDecimal emi = calculator.calculate(
				new BigDecimal("100000"),
				new BigDecimal("12"),
				60
		);

		Assertions.assertEquals(new BigDecimal("2224.44"),emi);
	}
}

package com.loanengine.LoanEngine.loan.internal.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "loan")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "loan_number",updatable = false,nullable = false,unique = true)
    private String loanNumber;

    @Column(name = "original_principal",updatable = false,nullable = false)
    private BigDecimal originalPrincipal;

    @Column(name = "outstanding_principal",nullable = false)
    private BigDecimal outstandingPrincipal;

    @Column(name = "monthly_installment")
    private BigDecimal monthlyInstallment;

    @Column(name = "currency_code")
    private CurrencyCode currencyCode;

    @Column(name = "annual_interest",updatable = false,nullable = false)
    private BigDecimal annualInterest;

    @Column(name = "original_term_months")
    private Integer originalTermMonths;

    @Column(name ="remaining_term_months")
    private Integer remainingTermMonths;

    @CreationTimestamp
    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    @Column(name = "loan_start_date",updatable = false,nullable = false)
    private OffsetDateTime loanStartDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "loan_status")
    private LoanStatus loanStatus;

    @Column(name = "close_date")
    private OffsetDateTime closeDate;
}

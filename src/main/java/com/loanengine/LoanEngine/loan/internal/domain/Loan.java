package com.loanengine.LoanEngine.loan.internal.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

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

    @Column(name = "customer_id",nullable = false,updatable = false)
    private Long customerId;

    @Column(name = "loan_number",updatable = false,nullable = false)
    private String loanNumber;

    @Column(name = "original_principal",updatable = false,nullable = false)
    private BigDecimal originalPrincipal;

    @Column(name = "outstanding_principal")
    private BigDecimal outstandingPrincipal;

    @Column(name = "annual_interest",updatable = false,nullable = false)
    private BigDecimal annualInterest;

    @Column(name = "original_term_months")
    private Integer originalTermMonths;

    @Column(name ="remianining_term_months")
    private Integer remainingTermMonths;


    @CreationTimestamp
    @Column(name = "start_date",updatable = false,nullable = false)
    private OffsetDateTime startDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "loan_status")
    private LoanStatus loanStatus;

    @Column(name = "close_date")
    private OffsetDateTime closeDate;
}

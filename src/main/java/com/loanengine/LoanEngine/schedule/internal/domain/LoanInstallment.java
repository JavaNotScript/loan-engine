package com.loanengine.LoanEngine.schedule.internal.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name ="loan_installment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoanInstallment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long installmentId;

    @ManyToOne
    @JoinColumn(name = "schedule_version")
    private LoanScheduleVersion scheduleVersion;

    @Column(name = "installment_number",updatable = false,nullable = false)
    private Integer installmentNumber;

    @Column(name = "opening_balance")
    private BigDecimal openingBalance;

    @Column(name = "closing_balance")
    private BigDecimal closingBalance;

    @Column(name = "principal_amount")
    private BigDecimal principalAmount;

    @Column(name = "interest_amount")
    private BigDecimal interestAmount;

    @Column(name = "payment_amount")
    private BigDecimal paymentAmount;

//    @Column(name = "paid_amount",nullable = false,updatable = false)
//    private BigDecimal paidAmount;
//
//    @Column(name = "paid_date")
//    private OffsetDateTime paidDate;

    @Column(name = "due_date")
    private OffsetDateTime dueDate;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "installment_status")
    private InstallmentStatus installmentStatus;
}

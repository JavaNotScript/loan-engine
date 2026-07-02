package com.loanengine.LoanEngine.schedule.internal.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;

@Entity
@Table(name = "loan_schedule_version")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoanScheduleVersion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleVersionId;

    @Column(name = "loan_id")
    private Long loanId;

    @Column(name = "version_number", updatable = false, nullable = false)
    private Long versionNumber;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private OffsetDateTime createdAt;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "schedule_status")
    private ScheduleVersionStatus scheduleStatus;
}

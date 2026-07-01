package com.loanengine.LoanEngine.loan.internal.repos;

import com.loanengine.LoanEngine.loan.internal.domain.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    @Query("""
            SELECT l FROM Loan l WHERE l.loanNumber= :loanNumber
            """)
    Optional<Loan> findByLoanNumber(@Param("loanNumber") String loanNumber);
}

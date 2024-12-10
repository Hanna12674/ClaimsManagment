package org.hannaweldehana.claimsmanagment.repository;

import org.hannaweldehana.claimsmanagment.model.Claim;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClaimStatusRepository extends JpaRepository<Claim, Long> {

    @Lazy
    // Custom query to find claims by status
    @Query("SELECT c FROM Claim c WHERE c.claimStatus = :status")
    List<Claim> findByStatus(@Param("status") String status);

    // Custom query to count claims by status
    @Query("SELECT COUNT(c) FROM Claim c WHERE c.claimStatus = :status")
    long countByStatus(@Param("status") String status);

    // Custom query to find claims with a specific status and customer
    @Query("SELECT c FROM Claim c WHERE c.claimStatus = :status AND c.customer.id = :customerId")
    List<Claim> findByStatusAndCustomerId(@Param("status") String status, @Param("customerId") Long customerId);
}
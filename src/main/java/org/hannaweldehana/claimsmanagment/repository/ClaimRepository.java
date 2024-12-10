package org.hannaweldehana.claimsmanagment.repository;

import org.hannaweldehana.claimsmanagment.model.Claim;
import org.hannaweldehana.claimsmanagment.model.ClaimStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClaimRepository extends JpaRepository<Claim, Long> {

    // Fetch claims by status
    List<Claim> findByStatus(ClaimStatus status);

    // Fetch claims by customer and status
    List<Claim> findByCustomerIdAndStatus(Long customerId, ClaimStatus status);

}

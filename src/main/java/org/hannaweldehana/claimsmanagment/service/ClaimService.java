package org.hannaweldehana.claimsmanagment.service;

import org.hannaweldehana.claimsmanagment.model.Claim;
import org.hannaweldehana.claimsmanagment.repository.ClaimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



@Service
public class ClaimService {

    @Autowired
    private ClaimRepository claimRepository;

    /**
     * Create or Update a Claim
     */
    public Claim saveClaim(Claim claim) {
        return claimRepository.save(claim);
    }

    /**
     * Get all Claims
     */
    public List<Claim> getAllClaims() {
        return claimRepository.findAll();
    }

    /**
     * Get Claim by ID
     */
    public Optional<Claim> getClaimById(Long id) {
        return claimRepository.findById(id);
    }

    /**
     * Get Claims by Customer ID
     */
    public List<Claim> getClaimsByCustomerId(Long customerId) {
        return claimRepository.findByCustomerId(customerId);
    }

    /**
     * Delete Claim by ID
     */
    public void deleteClaim(Long id) {
        claimRepository.deleteById(id);
    }
}
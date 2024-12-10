package org.hannaweldehana.claimsmanagment.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@Table(name = "claims")
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "claim_number", nullable = false, unique = true)
    private String claimNumber;

    @Column(name = "description", length = 500)
    private String description;

    @Enumerated(EnumType.STRING) // Use Enum for status
    @Column(name = "status", nullable = false)
    private ClaimStatus status;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "submitted_at", nullable = false, updatable = false)
    private LocalDateTime submittedAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Default constructor for JPA
    public Claim() {}

    // Parameterized constructor
    public Claim(String claimNumber, String description, ClaimStatus status, Customer customer) {
        this.claimNumber = claimNumber;
        this.description = description;
        this.status = status;
        this.customer = customer;
    }

    // Automatically set timestamps
    @PrePersist
    protected void onCreate() {
        submittedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
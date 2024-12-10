package org.hannaweldehana.claimsmanagment.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Getter
@Setter
@ToString
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private String name;

    @Column
    private String phone;

    @Column
    private String status;

    // Role: CUSTOMER or ADMIN
    @Column(nullable = false)
    private String role; // NEW field for role management

    // One Customer can have many Claims
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Claim> claims;

    // Default constructor
    public Customer() {}

    // Constructor with all fields
    public Customer(String username, String password, String email, String name, String phone, String status, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.status = status;
        this.role = role;
    }

    // Constructor for common fields
    public Customer(String username, String password, String email, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }
}
package org.hannaweldehana.claimsmanagment.repository;

import org.hannaweldehana.claimsmanagment.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Find a customer by their username
    Customer findByUsername(String username);

    // Custom query to find customers by name
    @Query("SELECT c FROM Customer c WHERE c.name = :name")
    List<Customer> findCustomersByName(@Param("name") String name);
}



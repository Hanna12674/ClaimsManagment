package org.hannaweldehana.claimsmanagment.service;

import lombok.extern.slf4j.Slf4j;
import org.hannaweldehana.claimsmanagment.model.Customer;
import org.hannaweldehana.claimsmanagment.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
@Slf4j
@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    /**
     * Create or Update a Customer
     *
     * @param customer Customer to be saved
     * @return Saved Customer
     */
    @Transactional
    public Customer saveCustomer(Customer customer) {
        log.info("Saving customer: {}", customer);
        return customerRepository.save(customer);
    }
    /**
     * Get all Customers
     *
     * @return List of Customers
     */
    public List<Customer> getAllCustomers() {
        log.info("Fetching all customers.");
        return customerRepository.findAll();
    }
    /**
     * Get Customer by ID
     *
     * @param id Customer ID
     * @return Optional<Customer>
     */
    public Optional<Customer> getCustomerById(Long id) {
        log.info("Fetching customer by ID: {}", id);
        return customerRepository.findById(id);
    }
    /**
     * Delete Customer by ID
     *
     * @param id Customer ID to delete
     */
    @Transactional
    public void deleteCustomer(Long id) {
        if (customerRepository.existsById(id)) {
            log.info("Deleting customer by ID: {}", id);
            customerRepository.deleteById(id);
        } else {
            log.warn("Attempted to delete non-existent customer with ID: {}", id);
            throw new IllegalArgumentException("Customer with ID " + id + " does not exist.");
        }
    }
    /**
     * Check if Customer Exists by ID
     *
     * @param id Customer ID
     * @return true if exists, false otherwise
     */
    public boolean existsById(Long id) {
        log.info("Checking existence of customer by ID: {}", id);
        return customerRepository.existsById(id);
    }
    /**
     * Authenticate Customer
     *
     * @param username Customer Username
     * @param password Customer Password
     * @return Customer if authenticated, null otherwise
     */
    public Customer authenticateCustomer(String username, String password) {
        log.info("Authenticating customer with username: {}", username);
        return customerRepository.findByUsernameAndPassword(username, password)
                .orElse(null);
    }
}

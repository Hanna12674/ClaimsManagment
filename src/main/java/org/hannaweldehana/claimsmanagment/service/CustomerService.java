package org.hannaweldehana.claimsmanagment.service;

import lombok.extern.slf4j.Slf4j;
import org.hannaweldehana.claimsmanagment.model.Customer;
import org.hannaweldehana.claimsmanagment.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;



@Slf4j
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Save or Update a Customer
     *
     * @param customer Customer to be saved
     * @return Saved Customer
     */
    @Transactional
    public Customer saveCustomer(Customer customer) {
        log.info("Saving customer: {}", customer);
        // Encrypt the password before saving
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
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
     * Check if a Customer Exists by ID
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
     */
    public Customer authenticateCustomer(String username, String password) {
        Optional<Customer> optionalCustomer = customerRepository.findByUsername(username);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            if (passwordEncoder.matches(password, customer.getPassword())) {
                return customer;
            }
        }
        return null; // Return null if authentication fails
    }

}
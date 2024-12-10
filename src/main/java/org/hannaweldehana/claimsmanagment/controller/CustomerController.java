package org.hannaweldehana.claimsmanagment.controller;

import org.hannaweldehana.claimsmanagment.model.Customer;
import org.hannaweldehana.claimsmanagment.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;


@Slf4j
@Controller
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;
    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    /**
     * Display the Sign-Up Page
     */
    @GetMapping("/signup")
    public String signUpPage() {
        return "sign_up"; // Returns the signup form template
    }
    /**
     * Handle Sign-Up Form Submission
     */
    @PostMapping("/signup")
    public String signUp(@ModelAttribute Customer customer, Model model) {
        try {
            customerService.saveCustomer(customer);
            //log.info("New customer registered: {}", customer.getUsername());
            return "redirect:/customers/login"; // Redirect to login after successful registration
        } catch (Exception e) {
            //log.error("Error during customer registration: {}", e.getMessage());
            model.addAttribute("error", "Registration failed. Please try again.");
            return "sign_up"; // Return to the signup form with an error message
        }
    }
    /**
     * Display the Login Page
     */
    @GetMapping("/login")
    public String loginPage() {
        return "login"; // Returns the login form template
    }
    /**
     * Handle Login Form Submission
     */
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {
        Customer authenticatedCustomer = customerService.authenticateCustomer(username, password);
        if (authenticatedCustomer != null) {
            session.setAttribute("loggedInUser", authenticatedCustomer); // Store user in session
            //log.info("Customer logged in: {}", authenticatedCustomer.getUsername());
            // Redirect based on role
            if ("ADMIN".equals(authenticatedCustomer.getRole())) {
                return "redirect:/admin/dashboard";
            } else {
                return "redirect:/claims/history";
            }
        } else {
            //log.warn("Failed login attempt for username: {}", username);
            model.addAttribute("error", "Invalid username or password");
            return "login"; // Return to the login form with an error message
        }
    }
    /**
     * Handle Logout
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        Customer loggedInUser = (Customer) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            //log.info("Customer logged out: {}", loggedInUser.getUsername());
        }
        session.invalidate(); // Clear session
        return "redirect:/customers/login"; // Redirect to login page after logout
    }
}
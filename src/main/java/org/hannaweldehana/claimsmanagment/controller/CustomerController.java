package org.hannaweldehana.claimsmanagment.controller;

import org.hannaweldehana.claimsmanagment.model.Customer;
import org.hannaweldehana.claimsmanagment.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // Login form page
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session, Model model) {
        Customer authenticatedCustomer = customerService.authenticateCustomer(username, password);
        if (authenticatedCustomer != null) {
            session.setAttribute("loggedInUser", authenticatedCustomer);
            if ("ADMIN".equals(authenticatedCustomer.getRole())) {
                return "redirect:/admin/dashboard";
            } else {
                return "redirect:/claims/history";
            }
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }

    @GetMapping("/signup")
    public String signUpPage() {
        return "sign_up"; // Sign-up form page
    }

    @PostMapping("/signup")
    public String signUp(@ModelAttribute Customer customer, Model model) {
        try {
            customerService.saveCustomer(customer);
            return "redirect:/customers/login"; // Redirect to login after successful registration
        } catch (Exception e) {
            model.addAttribute("error", "Registration failed. Please try again.");
            return "sign_up";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/customers/login"; // Redirect to login after logout
    }
}
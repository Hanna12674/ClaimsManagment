package org.hannaweldehana.claimsmanagment.controller;

import jakarta.servlet.http.HttpSession;
import org.hannaweldehana.claimsmanagment.model.Customer;
import org.hannaweldehana.claimsmanagment.service.ClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ClaimService claimService;

    @Autowired
    public AdminController(ClaimService claimService) {
        this.claimService = claimService;
    }

    @GetMapping("/dashboard")
    public String adminDashboard(HttpSession session, Model model) {
        Customer loggedInUser = (Customer) session.getAttribute("loggedInUser");
        if (loggedInUser != null && "ADMIN".equals(loggedInUser.getRole())) {
            model.addAttribute("claims", claimService.getAllClaims());
            return "admin"; // Admin dashboard template
        }
        return "redirect:/customers/login";
    }
}

package org.hannaweldehana.claimsmanagment.controller;

import org.hannaweldehana.claimsmanagment.model.ClaimStatus;
import org.hannaweldehana.claimsmanagment.service.ClaimService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.hannaweldehana.claimsmanagment.model.Claim;
import org.hannaweldehana.claimsmanagment.model.Customer;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/claims")
public class ClaimController {
    private final ClaimService claimService;
    @Autowired
    public ClaimController(ClaimService claimService) {
        this.claimService = claimService;
    }
    /**
     * View Claim History for Logged-in Customer
     */
    @GetMapping("/history")
    public String viewClaimHistory(HttpSession session, Model model) {
        Customer loggedInUser = (Customer) session.getAttribute("loggedInUser");
        if (loggedInUser != null && "CUSTOMER".equals(loggedInUser.getRole())) {
            List<Claim> claims = claimService.getClaimsByCustomerId(loggedInUser.getId());
            model.addAttribute("claims", claims);
            return "claim_history";
        }
        return "redirect:/customers/login";
    }
    /**
     * View Claim Details
     */
    @GetMapping("/{id}")
    public String viewClaimDetails(@PathVariable Long id, HttpSession session, Model model) {
        Customer loggedInUser = (Customer) session.getAttribute("loggedInUser");
        if (loggedInUser != null && "CUSTOMER".equals(loggedInUser.getRole())) {
            Claim claim = claimService.getClaimById(id).orElseThrow(() -> new RuntimeException("Claim not found"));
            model.addAttribute("claim", claim);
            return "claim_detail";
        }
        return "redirect:/customers/login";
    }
    /**
     * Display Claim Submission Form
     */
    @GetMapping("/submit")
    public String submitClaimForm(HttpSession session) {
        Customer loggedInUser = (Customer) session.getAttribute("loggedInUser");
        if (loggedInUser != null && "CUSTOMER".equals(loggedInUser.getRole())) {
            return "submit_claim";
        }
        return "redirect:/customers/login";
    }
    /**
     * Process Claim Submission
     */
    @PostMapping("/submit")
    public String submitClaim(@ModelAttribute Claim claim, HttpSession session) {
        Customer loggedInUser = (Customer) session.getAttribute("loggedInUser");
        if (loggedInUser != null && "CUSTOMER".equals(loggedInUser.getRole())) {
            claim.setCustomer(loggedInUser);
            claim.setStatus(ClaimStatus.PENDING);
            claimService.saveClaim(claim);
            return "redirect:/claims/history";
        }
        return "redirect:/customers/login";
    }
    /**
     * View All Claims for Admin
     */
    @GetMapping
    public String viewAllClaims(HttpSession session, Model model) {
        Customer loggedInUser = (Customer) session.getAttribute("loggedInUser");
        if (loggedInUser != null && "ADMIN".equals(loggedInUser.getRole())) {
            List<Claim> claims = claimService.getAllClaims();
            model.addAttribute("claims", claims);
            return "admin_claims";
        }
        return "redirect:/customers/login";
    }
}





















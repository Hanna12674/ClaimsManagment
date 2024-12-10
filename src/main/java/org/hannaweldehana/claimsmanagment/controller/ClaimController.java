package org.hannaweldehana.claimsmanagment.controller;

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
    @GetMapping("/history")
    public String viewClaimHistory(HttpSession session, Model model) {
        Customer loggedInUser = (Customer) session.getAttribute("loggedInUser");
        if (loggedInUser != null && "CUSTOMER".equals(loggedInUser.getRole())) {
            List<Claim> claims = claimService.getClaimsByCustomerId(loggedInUser.getId());
            model.addAttribute("claims", claims);
            return "claim_history";
        }
        return "redirect:/customers/login"; // Redirect unauthorized users
    }
    @GetMapping("/{id}")
    public String viewClaimDetails(HttpSession session, @PathVariable Long id, Model model) {
        Customer loggedInUser = (Customer) session.getAttribute("loggedInUser");
        if (loggedInUser != null && "CUSTOMER".equals(loggedInUser.getRole())) {
            Claim claim = claimService.getClaimById(id).orElseThrow(() -> new RuntimeException("Claim not found"));
            model.addAttribute("claim", claim);
            return "claim_detail";
        }
        return "redirect:/customers/login"; // Redirect unauthorized users
    }
    @PostMapping("/submit")
    public String submitClaim(HttpSession session, @ModelAttribute Claim claim) {
        Customer loggedInUser = (Customer) session.getAttribute("loggedInUser");
        if (loggedInUser != null && "CUSTOMER".equals(loggedInUser.getRole())) {
            claim.setCustomer(loggedInUser);
            claim.setStatus("Pending");
            claimService.saveClaim(claim);
            return "redirect:/claims/history";
        }
        return "redirect:/customers/login"; // Redirect unauthorized users
    }
    @PostMapping("/{id}/update")
    public String updateClaim(HttpSession session, @PathVariable Long id, @ModelAttribute Claim updatedClaim) {
        Customer loggedInUser = (Customer) session.getAttribute("loggedInUser");
        if (loggedInUser != null && "CUSTOMER".equals(loggedInUser.getRole())) {
            Claim claim = claimService.getClaimById(id).orElseThrow(() -> new RuntimeException("Claim not found"));
            claim.setDescription(updatedClaim.getDescription());
            claim.setStatus(updatedClaim.getStatus());
            claimService.saveClaim(claim);
            return "redirect:/claims/history";
        }
        return "redirect:/customers/login"; // Redirect unauthorized users
    }
}








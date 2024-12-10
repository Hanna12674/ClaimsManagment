package org.hannaweldehana.claimsmanagment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        // attributes to the model if needed
        return "index";  // Returns the "index.html" Thymeleaf template
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";  // Returns the "login.html" Thymeleaf template
    }

    @GetMapping("/submit")
    public String submitClaim(Model model) {
        return "submit_claim";  // Returns the "submit.html" Thymeleaf template
    }

    @GetMapping("/sign_up")
    public String signUp(Model model) {
        return "sign_up";  // Returns the "submit.html" Thymeleaf template
    }

    @GetMapping("/claim_status")
    public String claimStatus(Model model) {
        return "claim_status";  // Returns the "submit.html" Thymeleaf template
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        return "admin";  // Returns the "submit.html" Thymeleaf template
    }

    @GetMapping("/claim_history")
        public String claimHistory(Model model) {
            return "claim_history";  // Returns the "submit.html" Thymeleaf template
    }
        @GetMapping("/claim_detail")
        public String claimDetail(Model model) {
            return "claim_detail";  // Returns the "submit.html" Thymeleaf template
        }

}
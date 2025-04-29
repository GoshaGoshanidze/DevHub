package com.devhub.devhub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;

@Controller
public class AdminController {

    @GetMapping("/admin/dashboard")
    public String dashboard(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("username", user.getUsername());
        return "admin/dashboard";
    }

    @GetMapping("/admin/projects")
    public String projects() {
        return "admin/projects";
    }

    @GetMapping("/admin/messages")
    public String messages() {
        return "admin/messages";
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }
}

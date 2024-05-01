package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.demo.service.LogService;
import jakarta.servlet.http.HttpSession;
import com.example.demo.domain.login;
import com.example.demo.repository.LogRepo;

@Controller
public class LogController {

    @Autowired
    private LogService service;
    private boolean authenticated;
    private LogRepo rep;

    @GetMapping("/")
    public String api() {
        return "index";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
    @PostMapping("/log")
public String login(@ModelAttribute("user") login user, HttpSession session) {
    authenticated = service.authenticate(user);
    
    if (authenticated) {
        login completeUser = service.findByEmail(user.getEmail());
        int userId = completeUser.getUserId();
        session.setAttribute("userId", userId);
        session.setAttribute("user", completeUser);
        return "redirect:/home";
    } else {
        return "redirect:/login?error";
    }
}
    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/display")
    public String display() {
        return "display";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/index";
    }

    @GetMapping("/profile")
public String profile(Model model, HttpSession session) {
    login user = (login) session.getAttribute("user");
    model.addAttribute("user", user);
    return "profile";
}

}

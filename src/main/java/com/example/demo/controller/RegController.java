package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.service.LogService;
import com.example.demo.service.RegService;

import jakarta.servlet.http.HttpSession;

import com.example.demo.domain.login;

@Controller
public class RegController {
 
    @Autowired
    private RegService service;

    @Autowired
    private LogService logservice;
 
    @GetMapping("/register")
    public String register()
    {
 
        return "register";
    }
 
    @PostMapping("/reg")
    public String login(@ModelAttribute("user") login user,HttpSession session) {
 
        service.saveUser(user.getUsername(),user.getEmail(),user.getPassword(),user.getDateofbirth(),user.getCompanyname(),user.getLocation());
        login completeUser = logservice.findByEmail(user.getEmail());
        session.setAttribute("user", completeUser);    
        int userId = completeUser.getUserId();
        session.setAttribute("userId", userId);
        return "redirect:/home";
        
    }
 
   
 
}

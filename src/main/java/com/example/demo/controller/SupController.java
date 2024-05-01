package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.domain.supplier;
import com.example.demo.service.SupService;

import jakarta.servlet.http.HttpSession;

@Controller
public class SupController {

    @Autowired
    private SupService supService;

    @GetMapping("/supplier")
    public String supplier() {
        return "supplier";
    }

    @PostMapping("/sup")
    public String supplier(@ModelAttribute("supplier") supplier supplier,HttpSession session) {
        int userId = (int) session.getAttribute("userId");
        supplier.setUserId(userId);
        supService.saveUser(supplier.getSupid(), supplier.getSuppliername(), supplier.getAddress(), supplier.getPhone(),supplier.getEmail(),supplier.getUserId());
        return "redirect:/supp";
    }

    @GetMapping("/supp")
    public String supplierlist(Model model, HttpSession session) {
        int userId = (int) session.getAttribute("userId");
        List<supplier> supplierList = supService.getAllSuppliers(userId);
        model.addAttribute("suppliers", supplierList);
        return "supplier";
    }

    @GetMapping("/delete1/{id}")
    public String deleteSupplier(@PathVariable("id") int id) {
        supService.deleteSupplierById(id);
        return "redirect:/supp";
    }

    @GetMapping("/edit1/{id}")
    public String editSupplier(@PathVariable("id") int id, Model model) {
        supplier supplier = supService.getSupplierById(id);
        model.addAttribute("supplier", supplier);
        return "edit-supplier";
    }

    @PostMapping("/edit1/{id}")
    public String updateSupplier(@PathVariable("id") int id, @ModelAttribute("supplier") supplier updatedSupplier) {
        supService.updateSupplier(id, updatedSupplier);
        return "redirect:/supp";
    }
}

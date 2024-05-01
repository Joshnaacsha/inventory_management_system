package com.example.demo.controller;

import com.example.demo.domain.product;
import com.example.demo.service.ProService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.sql.Date;
import java.util.List;

@Controller
public class ProdController {

    @Autowired
    private ProService proService;

    @GetMapping("/product")
    public String product() {
        return "product";
    }

    @PostMapping("/prod")
    public String product(@ModelAttribute("product") product product, HttpSession session) {
        int userId = (int) session.getAttribute("userId");
        product.setUserId(userId);
        proService.saveOrUpdateProduct(product.getProdid(), product.getProdname(), product.getProdqty(), product.getProdprice(), product.getProdexp(), product.getSupid(), userId);
        return "redirect:/products";
    }

    @GetMapping("/products")
    public String productList(Model model, HttpSession session) {
        int userId = (int) session.getAttribute("userId");
        List<product> productList = proService.getAllProductsByUserId(userId);
        List<product> closeToExpiryProducts = proService.getProductsCloseToExpiry(7,userId); 
        model.addAttribute("products", productList);
        model.addAttribute("closeToExpiryProducts", closeToExpiryProducts);
        return "product";
    }
    @GetMapping("/products_frontend")
    public ResponseEntity<List<product>> productListAPI(HttpSession session) {
        int userId = (int) session.getAttribute("userId");
        List<product> productList = proService.getAllProductsByUserId(userId);
        List<product> closeToExpiryProducts = proService.getProductsCloseToExpiry(7,userId);
        return ResponseEntity.ok()
                .body(productList);
    }

    @GetMapping("/delete/{id}/{exp}")
public String deleteProduct(@PathVariable("id") int id, @PathVariable("exp") Date exp, HttpSession session) {
    int userId = (int) session.getAttribute("userId");
    proService.deleteProductByIdAndExp(id, exp, userId);
    return "redirect:/products";
}

@GetMapping("/edit/{id}/{exp}")
public String editProduct(@PathVariable("id") int id, @PathVariable("exp") Date exp, Model model, HttpSession session) {
    int userId = (int) session.getAttribute("userId");
    product product = proService.getProductByIdAndExp(id, exp, userId);
    model.addAttribute("product", product);
    return "edit-product";
}

@PostMapping("/edit/{id}/{exp}")
public String updateProduct(@PathVariable("id") int id, @PathVariable("exp") Date exp, @ModelAttribute("product") product updatedProduct, HttpSession session) {
    int userId = (int) session.getAttribute("userId");
    proService.updateProduct(id, exp, updatedProduct, userId);
    return "redirect:/products";
}

}

package com.example.demo.controller;

import com.example.demo.domain.bill;
import com.example.demo.domain.product;
import com.example.demo.service.BillService;
import com.example.demo.service.ProService;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BillController {

    @Autowired
    private BillService billService;

    @Autowired
    private ProService productService;
    
    @GetMapping("/bill")
    public String billForm() {
        return "bill";
    }

    @PostMapping("/billgen")
public String generateBill(@ModelAttribute("bill") bill bill, HttpSession session) {
    int userId = (int) session.getAttribute("userId");
        bill.setUserid(userId);
        List<product> productList = productService.getProductByIdAndUserId(bill.getProductid(), userId);
        if (!productList.isEmpty()) {
        Date expiryDate = productList.get(0).getProdexp();
        billService.savebill(bill.getBillid(), bill.getProductid(),bill.getProductname(), bill.getQuantity(), expiryDate, bill.getUserid());
        return "redirect:/billprod/" + bill.getProductid() + "/" + expiryDate + "/" + bill.getQuantity() + "/" + bill.getUserid();
    } else {
        return "redirect:/error";
    }
}

    @GetMapping("/billprod/{id}/{exp}/{quantity}/{userId}")
    public String showBillProducts(@PathVariable("id") int id, @PathVariable("exp") Date exp, @PathVariable("quantity") int quantity, @PathVariable("userId") int userId, Model model) {
        product product = productService.getProductByIdAndExp(id, exp, userId);
        float totalPrice = product.getProdprice() * quantity;
        model.addAttribute("product", product);
        model.addAttribute("quantity", quantity);
        model.addAttribute("totalPrice", totalPrice);
        return "bill"; 
    }
}

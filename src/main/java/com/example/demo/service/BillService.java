package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.bill;
import com.example.demo.domain.product;
import com.example.demo.domain.productkey;
import com.example.demo.repository.BillRepo;
import com.example.demo.repository.ProRepo;

import java.sql.Date;
import java.util.NoSuchElementException;

@Service
public class BillService {

    @Autowired
    private BillRepo billRepository;

    @Autowired
    private ProRepo proRepository;

    @Transactional
    public void savebill(int billid, int productid, String productname, int quantity, Date prodexp, int userId) {
        bill bill = new bill();
        bill.setBillid(billid);
        bill.setProductid(productid);
        bill.setProductname(productname);
        bill.setQuantity(quantity);
        bill.setProdexp(prodexp);
        bill.setUserid(userId);
        billRepository.save(bill);
    
        productkey key = new productkey(productid, prodexp);
        product product = proRepository.findByProdidAndProdexpAndUserId(productid, prodexp, userId);
    
        if (product != null) {
            int updatedQuantity = product.getProdqty() - quantity;
            if (updatedQuantity >= 0) {
                product.setProdqty(updatedQuantity);
                proRepository.save(product);
            } else {
                throw new IllegalArgumentException("Insufficient quantity for product with id: " + productid);
            }
        } else {
            throw new NoSuchElementException("Product not found with id: " + productid + " for user: " + userId);
        }
    }
}

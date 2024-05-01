package com.example.demo.service;

import com.example.demo.domain.product;
import com.example.demo.domain.supplier;
import com.example.demo.repository.ProRepo;
import com.example.demo.repository.SupRepo;

import jakarta.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProService {

    @Autowired
    private ProRepo proRepository;

    @Transactional
    public void saveOrUpdateProduct(int prodid, String prodname, int prodqty, float prodprice, Date prodexp, int supid, int userId) {
        product existingProduct = proRepository.findByProdidAndProdexpAndUserId(prodid, prodexp, userId);
        if (existingProduct != null) {
            int updatedQuantity = existingProduct.getProdqty() + prodqty;
            existingProduct.setProdqty(updatedQuantity);
            proRepository.save(existingProduct);
        } else {
            product newProduct = new product(prodid, prodexp, prodname, prodqty, prodprice, supid, userId);
            proRepository.save(newProduct);
        }
    }

    public void deleteProductByIdAndExp(int prodid, Date prodexp, int userId) {
        product existingProduct = proRepository.findFirstByProdidAndProdexpAndUserId(prodid, prodexp, userId);
        if (existingProduct != null) {
            proRepository.delete(existingProduct);
        } else {
            throw new RuntimeException("Product with prodid " + prodid + " and prodexp " + prodexp + " not found for user " + userId);
        }
    }

    public product getProductByIdAndExp(int prodid, Date prodexp, int userId) {
        return proRepository.findByProdidAndProdexpAndUserId(prodid, prodexp, userId);
    }

    @Transactional
    public void updateProduct(int prodid, Date prodexp, product updatedProduct, int userId) {
        product existingProduct = proRepository.findByProdidAndProdexpAndUserId(prodid, prodexp, userId);
        if (existingProduct != null) {
            existingProduct.setProdname(updatedProduct.getProdname());
            existingProduct.setProdqty(updatedProduct.getProdqty());
            existingProduct.setProdprice(updatedProduct.getProdprice());
            existingProduct.setSupid(updatedProduct.getSupid());
            proRepository.save(existingProduct);
        } else {
            throw new RuntimeException("Product with prodid " + prodid + " and prodexp " + prodexp + " not found for user " + userId);
        }
    }

    public List<product> getAllProductsByUserId(int userId) {
        return proRepository.findAllByUserId(userId);
    }

    @Transactional
    public void deleteProductsIfQuantityZero() {
        List<product> products = proRepository.findAll();
        for (product product : products) {
            if (product.getProdqty() == 0) {
                proRepository.delete(product);
            }
        }
    }

    public List<product> getProductByIdAndUserId(int productid, int userId) {
        return proRepository.findByProdidAndUserId(productid, userId);
    }
    public List<product> getProductsCloseToExpiry(int days, int userId) {
        LocalDate expiryThreshold = LocalDate.now().plusDays(days);
        List<product> userProducts = proRepository.findAllByUserId(userId);
        List<product> closeToExpiryProducts = userProducts.stream()
                .filter(product -> isCloseToExpiry(product.getProdexp(), expiryThreshold))
                .collect(Collectors.toList());

        return closeToExpiryProducts;
    }
    private boolean isCloseToExpiry(Date expiryDate, LocalDate expiryThreshold) {
        LocalDate expiryLocalDate = expiryDate.toLocalDate();
        return expiryLocalDate.isBefore(expiryThreshold);
    }
    @Autowired
    private JavaMailSender javaMailSender; 
    @Autowired
    private SupRepo supRepository;
    @Transactional
    public void checkAndSendEmailNotifications() {        
        LocalDate thresholdDate = LocalDate.now().plusDays(5);
        Date sqlThresholdDate = Date.valueOf(thresholdDate);

        List<product> lowStockProducts = proRepository.findByProdqtyLessThan(6); 
        for (product product : lowStockProducts) {
            sendEmailToSupplier(product);
        }
    }

    private void sendEmailToSupplier(product product) {
        int supid = product.getSupid();
        supplier supplier = supRepository.findById(supid).orElse(null);
        if (supplier != null) {
            try {
                MimeMessage message = javaMailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true);
                helper.setTo(supplier.getEmail());
                helper.setSubject("Product Restocking Reminder");
                helper.setText("Dear " + supplier.getSuppliername() + ",\n\n" +
                        "This is a reminder to restock the product: " + product.getProdname() +
                        ". The current quantity is below 6.\n\n" +
                        "Thank you,\nStockwise");
                javaMailSender.send(message);
                System.out.println("Mail sent successfully");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Supplier not found for product with ID: " + product.getProdid());
        }
    }
}

package com.example.demo.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.service.ProService;

@Component
public class ProductCleanupTask {

    private final ProService productService;

    public ProductCleanupTask(ProService productService) {
        this.productService = productService;
    }

    @Scheduled(fixedRate = 86400000) 
    public void deleteZeroQuantityProducts() {
        productService.deleteProductsIfQuantityZero();
    }
}

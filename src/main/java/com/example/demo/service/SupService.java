package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.supplier;
import com.example.demo.repository.SupRepo;

@Service
public class SupService {

    @Autowired
    private SupRepo supplierRepository;

    public void saveUser(int supid, String suppliername, String address, long phone, String email,int userId) {
        supplier user = new supplier();
        user.setSupid(supid);
        user.setSuppliername(suppliername);
        user.setAddress(address);
        user.setPhone(phone);
        user.setEmail(email);
        user.setUserId(userId);
        supplierRepository.save(user);
    }

    public void deleteSupplierById(int id) {
        supplierRepository.deleteById(id);
    }

    public supplier getSupplierById(int id) {
        return supplierRepository.findById(id).orElse(null);
    }

    public void updateSupplier(int id, supplier updatedSupplier) {
        supplier existingSupplier = supplierRepository.findById(id).orElse(null);
        if (existingSupplier != null) {
            existingSupplier.setSuppliername(updatedSupplier.getSuppliername());
            existingSupplier.setAddress(updatedSupplier.getAddress());
            existingSupplier.setPhone(updatedSupplier.getPhone());
            supplierRepository.save(existingSupplier);
        }
    }

    public List<supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    public List<supplier> getAllSuppliers(int userId) {
    
        return supplierRepository.findAllByUserId(userId);
    }
}

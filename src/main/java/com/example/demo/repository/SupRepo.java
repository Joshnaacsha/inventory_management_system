package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.domain.supplier;

@Repository
public interface SupRepo  extends JpaRepository<supplier,Integer>{
    List<supplier> findBySupidOrSuppliernameOrAddressOrPhone(int supid, String suppliername, String address , long phone);
     List<supplier> findAll();
    List<supplier> findAllByUserId(int userId);
}

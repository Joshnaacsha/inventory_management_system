package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.bill;

@Repository
public interface BillRepo extends JpaRepository<bill, Integer> {
   
}

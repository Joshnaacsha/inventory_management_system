package com.example.demo.repository;

import com.example.demo.domain.product;
import com.example.demo.domain.productkey;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProRepo extends JpaRepository<product, productkey> {

    void deleteByProdidAndProdexpAndUserId(int prodid, Date prodexp, int userId);
    product findByProdidAndProdexpAndUserId(int prodid, Date prodexp, int userId);
    product findFirstByProdidAndProdexpAndUserId(int prodid, Date prodexp, int userId);
    List<product> findByProdidAndUserId(int prodid, int userId);
    List<product> findAllByUserId(int userId);
    List<product> findByUserId(int userId);
    List<product> findByProdqtyLessThan(int i);
    List<product> findByProdexpBeforeAndUserId(LocalDate expiryDate, int userId);
}

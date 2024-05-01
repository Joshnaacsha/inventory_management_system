package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.domain.login;

@Repository
public interface LogRepo extends JpaRepository<login,Integer>{

login findByEmailAndPassword(String email, String password);
login findByEmail(String email);
login findByUsername(String username);

}

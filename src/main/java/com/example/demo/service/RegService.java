package com.example.demo.service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.login;
import com.example.demo.repository.LogRepo;

@Service
public class RegService {
    @Autowired
    private LogRepo userRepository;

    public void saveUser(String username, String email, String password, Date dateofbirth, String companyname,
            String location) {
        login user = new login();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setDateofbirth(dateofbirth);
        user.setCompanyname(companyname);
        user.setLocation(location);
        userRepository.save(user);

    }
}

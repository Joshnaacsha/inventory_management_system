package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.login;
import com.example.demo.repository.LogRepo;

@Service
public class LogService {
            @Autowired
            private LogRepo rep;
            public login log(String email, String password){
                login user = rep.findByEmailAndPassword(email, password);
                return user;
            }
            public boolean authenticate(login user) {
                login storedUser = rep.findByEmail(user.getEmail());
                if (storedUser.getEmail() != null && storedUser.getPassword().equals(user.getPassword())) {
                    return true; 
                } else {
                    return false; 
                }
            }
            public login findByEmail(String email) {
                return rep.findByEmail(email);
            }

}

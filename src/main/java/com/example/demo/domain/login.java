package com.example.demo.domain;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="credentials1")
public class login {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int userId;
    private String username;
    private String password;
    private String email;
    private Date dateofbirth;
    private String companyname;
    private String location;
    
    public login(String username, String password, String email,Date dateofbirth, String companyname, String location) {
        this.username = username;
        this.password = password;
        this.email= email;
        this.dateofbirth=dateofbirth;
        this.companyname=companyname;
        this.location=location;
    }
    public login() {
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Date getDateofbirth() {
        return dateofbirth;
    }
    public void setDateofbirth(Date dateofbirth) {
        this.dateofbirth = dateofbirth;
    }
    public String getCompanyname() {
        return companyname;
    }
    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
}

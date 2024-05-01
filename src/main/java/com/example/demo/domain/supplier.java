package com.example.demo.domain;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "supplier")
public class supplier {
    @Id
    private int supid;
    private String suppliername;
    private String  address;
    private long  phone;
    private int userId;
    private String email;
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getSupid() {
        return supid;
    }
    public void setSupid(int supid) {
        this.supid = supid;
    }
    public String getSuppliername() {
        return suppliername;
    }
    public void setSuppliername(String suppliername) {
        this.suppliername = suppliername;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public supplier(int supid, String suppliername, String address, long phone,String email) {
        this.supid = supid;
        this.suppliername = suppliername;
        this.address = address;
        this.phone = phone;
        this.email=email;
    }
    public supplier() {
    }
    public long getPhone() {
        return phone;
    }
    public void setPhone(long phone) {
        this.phone = phone;
    }
    
}

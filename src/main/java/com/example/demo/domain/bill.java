package com.example.demo.domain;

import java.sql.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="bill")
public class bill {
    @Id
    private int billid;
    private int productid;
    private int quantity;
    private String productname;
    private Date prodexp;
    private int userId; 

    public bill(int billid, int productid, int quantity, String productname, Date prodexp, int userId) {
        this.billid = billid;
        this.productid = productid;
        this.quantity = quantity;
        this.productname = productname;
        this.prodexp=prodexp;
        this.userId = userId;
    }
    public bill(){

    }

    public int getBillid() {
        return billid;
    }

    public void setBillid(int billid) {
        this.billid = billid;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public Date getProdexp() {
        return prodexp;
    }

    public void setProdexp(Date prodexp) {
        this.prodexp = prodexp;
    }

    public int getUserid() {
        return userId;
    }

    public void setUserid(int userId) {
        this.userId = userId;
    }
}

package com.example.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "product")
@IdClass(productkey.class)
public class product implements Serializable {
    @Id
    private int prodid;  
    @Id
    private Date prodexp;
    private String prodname;
    private int prodqty;
    private float prodprice;
    private int supid;
    @Column(name = "userId")
    private int userId; 

    public product() {
    }

    public product(int prodid, Date prodexp, String prodname, int prodqty, float prodprice, int supid, int userId) {
        this.prodid = prodid;
        this.prodexp = prodexp;
        this.prodname = prodname;
        this.prodqty = prodqty;
        this.prodprice = prodprice;
        this.supid = supid;
        this.userId = userId; 
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProdid() {
        return prodid;
    }

    public void setProdid(int prodid) {
        this.prodid = prodid;
    }

    public Date getProdexp() {
        return prodexp;
    }

    public void setProdexp(Date prodexp) {
        this.prodexp = prodexp;
    }

    public String getProdname() {
        return prodname;
    }

    public void setProdname(String prodname) {
        this.prodname = prodname;
    }

    public int getProdqty() {
        return prodqty;
    }

    public void setProdqty(int prodqty) {
        this.prodqty = prodqty;
    }

    public float getProdprice() {
        return prodprice;
    }

    public void setProdprice(float prodprice) {
        this.prodprice = prodprice;
    }

    public int getSupid() {
        return supid;
    }

    public void setSupid(int supid) {
        this.supid = supid;
    }

}

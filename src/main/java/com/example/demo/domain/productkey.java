package com.example.demo.domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class productkey implements Serializable {
    private int prodid;
    private Date prodexp;

    public productkey() {
    }

    public productkey(int prodid, Date prodexp) {
        this.prodid = prodid;
        this.prodexp = prodexp;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        productkey that = (productkey) o;
        return prodid == that.prodid && Objects.equals(prodexp, that.prodexp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prodid, prodexp);
    }
}


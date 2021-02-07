package com.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="carts")
public class Cart extends AbstractEntity {

    @ManyToOne
    private User user;

    @Column
    private String status;

    @ManyToMany(mappedBy="carts", fetch = FetchType.EAGER)
    private List<Product> products;

    public Cart() {
        products = new ArrayList<>();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}

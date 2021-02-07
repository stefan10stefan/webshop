package com.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="products")
public class Product extends AbstractEntity{

    @Column
    private String name;
    @Column
    private String description;
    @Column(length=1000000000)
    private String image;
    @Column
    private double price;
    @ManyToOne
    private Shop shop;
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "cart_product",
            joinColumns = { @JoinColumn(name = "product_id") },
            inverseJoinColumns = { @JoinColumn(name = "cart_id") }
    )
    private List<Cart> carts;

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
}

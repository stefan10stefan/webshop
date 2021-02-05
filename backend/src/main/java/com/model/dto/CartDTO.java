package com.model.dto;

import com.model.Cart;
import com.model.Product;

import java.util.List;

public class CartDTO {

    private Long id;

    private Long userId;

    private String status;

    private List<ProductDTO> products;

    public CartDTO() {}

    public CartDTO(Cart cart) {

        id = cart.getId();
        userId = cart.getUser() != null ? cart.getUser().getId() : -1;
        status = cart.getStatus();

        for(Product p: cart.getProducts()) {
            products.add(new ProductDTO(p));
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }
}

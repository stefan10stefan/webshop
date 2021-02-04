package com.model.dto;


import com.model.Product;

public class ProductDTO extends AbstractEntityDTO {

    private String name;
    private String description;
    private String image;
    private double price;
    private Long shopId;

    public ProductDTO(Product product) {
        name = product.getName();
        description = product.getDescription();
        image = product.getImage();
        price = product.getPrice();
        shopId = product.getShop() != null ? product.getShop().getId() : -1;
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

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }
}

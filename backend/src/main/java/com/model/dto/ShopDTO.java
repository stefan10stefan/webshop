package com.model.dto;

import com.model.Shop;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


public class ShopDTO extends AbstractEntityDTO {

    private String name;
    private double lat;
    private double lng;

    public ShopDTO() {}

    public ShopDTO(Shop shop) {
        id = shop.getId();
        name = shop.getName();
        lat = shop.getLat();
        lng = shop.getLng();
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package com.model.dto;

import com.model.Shop;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


public class ShopDTO extends AbstractEntityDTO {

    private String name;
    private Long lat;
    private Long lng;

    public ShopDTO(Shop shop) {
        name = shop.getName();
        lat = shop.getLat();
        lng = shop.getLng();
    }

    public Long getLat() {
        return lat;
    }

    public void setLat(Long lat) {
        this.lat = lat;
    }

    public Long getLng() {
        return lng;
    }

    public void setLng(Long lng) {
        this.lng = lng;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

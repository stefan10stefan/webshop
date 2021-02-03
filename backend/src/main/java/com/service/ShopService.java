package com.service;

import com.model.Shop;
import com.model.dto.ShopDTO;

public interface ShopService {
    public Shop add(ShopDTO shop);
    public Shop edit(ShopDTO shop);
    public Shop get(Long id);
}

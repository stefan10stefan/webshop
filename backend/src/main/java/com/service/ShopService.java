package com.service;

import com.model.Shop;
import com.model.dto.ShopDTO;

import java.util.List;

public interface ShopService {
    ShopDTO add(ShopDTO shop);
    ShopDTO edit(ShopDTO shop);
    ShopDTO get(Long id);
    List<ShopDTO> search(String term);
}

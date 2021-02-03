package com.service;

import com.model.Shop;
import com.model.dto.ShopDTO;
import com.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopRepository shopRepository;

    @Override
    public Shop add(ShopDTO shop) {
        return null;
    }

    @Override
    public Shop edit(ShopDTO shop) {
        return null;
    }

    @Override
    public Shop get(Long id) {
        return shopRepository.getOne(id);
    }
}

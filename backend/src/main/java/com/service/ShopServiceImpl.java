package com.service;

import com.model.Shop;
import com.model.dto.ShopDTO;
import com.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopRepository shopRepository;

    @Override
    public ShopDTO add(ShopDTO shopDTO) {

        Shop shop = new Shop();
        shop.setName(shopDTO.getName());
        shop.setLat(shopDTO.getLat());
        shop.setLng(shopDTO.getLng());
        shop.setDeleted(false);

        return new ShopDTO(shop);
    }

    @Override
    public ShopDTO edit(ShopDTO shopDTO) {

        Shop shop = shopRepository.getOne(shopDTO.getId());
        shop.setName(shopDTO.getName());
        shop.setLat(shopDTO.getLat());
        shop.setLng(shopDTO.getLng());

        return new ShopDTO(shop);
    }

    @Override
    public ShopDTO get(Long id) {
        return new ShopDTO(shopRepository.getOne(id));
    }

    @Override
    public List<ShopDTO> search(String term) {

        List<Shop> shops = shopRepository.findByNameContaining(term);
        List<ShopDTO> shopsDTO = new ArrayList<>();

        for(Shop s: shops) {
            shopsDTO.add(new ShopDTO(s));
        }

        return shopsDTO;
    }
}

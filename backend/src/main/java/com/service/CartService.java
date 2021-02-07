package com.service;

import com.model.Cart;
import com.model.dto.CartDTO;

import java.util.List;

public interface CartService {

    List<CartDTO> getHistory();
    CartDTO getCurrent();
    CartDTO addProduct(Long productId);
    CartDTO buy();
}

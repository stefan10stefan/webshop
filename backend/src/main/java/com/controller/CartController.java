package com.controller;

import com.model.dto.CartDTO;
import com.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping(path="/history")
    public List<CartDTO> getHistory() {
        return cartService.getHistory();
    }

    @GetMapping(path="/")
    public CartDTO getCurrent() {
        return cartService.getCurrent();
    }

    @PostMapping(path="/{productId}")
    CartDTO addProduct(@PathVariable("productId") Long productId){
        return cartService.addProduct(productId);
    }

    @PutMapping(path="/buy")
    public CartDTO buy() {
        return cartService.buy();
    }
}

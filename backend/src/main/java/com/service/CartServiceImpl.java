package com.service;

import com.model.Cart;
import com.model.Product;
import com.model.dto.CartDTO;
import com.model.dto.UserDTO;
import com.repository.CartRepository;
import com.repository.ProductRepository;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<CartDTO> getHistory() {

        UserDTO currentUser = userService.getCurrentUser();

        if(currentUser == null) {
            return new ArrayList<CartDTO>();
        }

        List<CartDTO> carts = new ArrayList<>();

        for(Cart c: cartRepository.findByUserIdAndStatus(userRepository.getOne(currentUser.getId()),
                "FINISH")) {
            carts.add(new CartDTO(c));
        }

        return carts;
    }

    @Override
    public CartDTO getCurrent() {

        UserDTO currentUser = userService.getCurrentUser();

        if(currentUser == null) {
            return null;
        }

        List<Cart> carts = cartRepository.findByUserIdAndStatus(userRepository.getOne(currentUser.getId()),
                "ACTIVE");


        if(carts == null || carts.size() == 0) {

            Cart cart = new Cart();
            cart.setStatus("ACTIVE");
            cart.setUser(userRepository.getOne(currentUser.getId()));
            cart.setDeleted(false);

            cartRepository.save(cart);
            return new CartDTO(cart);
        }

        return new CartDTO(carts.get(0));
    }

    @Override
    public CartDTO addProduct(Long productId) {
        CartDTO cartDTO = getCurrent();

        if(cartDTO == null) {
            return  null;
        }

        Product product = productRepository.getOne(productId);
        Cart cart = cartRepository.getOne(cartDTO.getId());

        if(cart.getProducts() == null) {
            cart.setProducts(new ArrayList<Product>());
        }

        cart.getProducts().add(product);

        cart = cartRepository.save(cart);

        if(product.getCarts() == null) {
            product.setCarts(new ArrayList<>());
        }

        product.getCarts().add(cart);

        productRepository.save(product);

        return new CartDTO(cart);
    }

    @Override
    public CartDTO buy() {
        CartDTO cartDTO = getCurrent();

        if(cartDTO == null) {
            return  null;
        }

        Cart cart = cartRepository.getOne(cartDTO.getId());

        cart.setStatus("FINISH");

        cart = cartRepository.save(cart);

        return new CartDTO(cart);
    }
}

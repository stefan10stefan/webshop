package com.service;

import com.model.Product;
import com.model.dto.ProductDTO;
import com.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product add(ProductDTO product) {
        return null;
    }

    @Override
    public Product edit(ProductDTO product) {
        return null;
    }

    @Override
    public Product get(Long id) {
        return productRepository.getOne(id);
    }
}

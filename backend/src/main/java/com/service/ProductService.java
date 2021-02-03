package com.service;

import com.model.Product;
import com.model.dto.ProductDTO;

public interface ProductService {
    public Product add(ProductDTO product);
    public Product edit(ProductDTO product);
    public Product get(Long id);
}

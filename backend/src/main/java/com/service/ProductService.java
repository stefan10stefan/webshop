package com.service;

import com.model.Product;
import com.model.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    ProductDTO add(ProductDTO product);
    ProductDTO edit(ProductDTO product);
    ProductDTO get(Long id);

    List<ProductDTO> search(String term);
}

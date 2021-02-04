package com.service;

import com.model.Product;
import com.model.Shop;
import com.model.dto.ProductDTO;
import com.model.dto.ShopDTO;
import com.repository.ProductRepository;
import com.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ShopRepository shopRepository;

    @Override
    public ProductDTO add(ProductDTO productDTO) {

        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setImage(productDTO.getImage());
        product.setShop(shopRepository.getOne(productDTO.getShopId()));
        product.setDeleted(false);

        return new ProductDTO(productRepository.save(product));
    }

    @Override
    public ProductDTO edit(ProductDTO productDTO) {

        Product product = productRepository.getOne(productDTO.getId());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setImage(productDTO.getImage());
        product.setShop(shopRepository.getOne(productDTO.getShopId()));

        return new ProductDTO(productRepository.save(product));
    }

    @Override
    public ProductDTO get(Long id) {
        return new ProductDTO(productRepository.getOne(id));
    }

    @Override
    public List<ProductDTO> search(String term) {

        List<Product> products = productRepository.findByNameContaining(term);
        List<ProductDTO> productsDTO = new ArrayList<>();

        for(Product p: products) {
            productsDTO.add(new ProductDTO(p));
        }

        return productsDTO;
    }
}

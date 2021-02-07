package com.controller;

import com.model.dto.ProductDTO;
import com.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(path="/{id}")
    public ProductDTO get(@PathVariable("id") Long id) {
        return productService.get(id);
    }

    @GetMapping(path="/search")
    public List<ProductDTO> get(@RequestParam("term") String term) {
        return productService.search(term);
    }

    @PostMapping(path="/")
    public ProductDTO add(@RequestBody ProductDTO product) {
        return  productService.add(product);
    }

    @PutMapping(path="/")
    public ProductDTO edit(@RequestBody ProductDTO product) {
        return productService.edit(product);
    }
}

package dev.shubham.productService.services;

import dev.shubham.productService.dtos.GenericProductDto;

public interface ProductService {
    GenericProductDto createProduct(GenericProductDto product);

    GenericProductDto getProductById(Long id);
}

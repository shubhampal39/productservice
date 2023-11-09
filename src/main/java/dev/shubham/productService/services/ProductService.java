package dev.shubham.productService.services;

import dev.shubham.productService.dtos.GenericProductDto;

import java.util.List;

public interface ProductService {
    GenericProductDto createProduct(GenericProductDto product);

    GenericProductDto getProductById(Long id);

    List<GenericProductDto> getProducts();

     GenericProductDto deleteProduct(Long id);
}

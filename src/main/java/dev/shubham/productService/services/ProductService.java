package dev.shubham.productService.services;

import dev.shubham.productService.Exceptions.NotFoundException;
import dev.shubham.productService.dtos.GenericProductDto;
import java.util.List;

public interface ProductService {
    GenericProductDto createProduct(GenericProductDto product);

    GenericProductDto getProductById(Long id) throws NotFoundException;

    List<GenericProductDto> getProducts();

     GenericProductDto deleteProduct(Long id);
}

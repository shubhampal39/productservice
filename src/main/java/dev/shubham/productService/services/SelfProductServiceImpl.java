package dev.shubham.productService.services;


import dev.shubham.productService.dtos.GenericProductDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("selfProductService")
public class SelfProductServiceImpl implements ProductService
{
    public GenericProductDto getProductById(Long Id)
    {
        return null;
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        return null;
    }

    @Override
    public GenericProductDto deleteProduct(Long id) {
        return null;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto product) {
        return null;
    }
}

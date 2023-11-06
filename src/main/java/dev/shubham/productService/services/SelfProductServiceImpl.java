package dev.shubham.productService.services;


import dev.shubham.productService.dtos.GenericProductDto;
import org.springframework.stereotype.Service;

@Service("selfProductService")
public class SelfProductServiceImpl implements ProductService
{
    public GenericProductDto getProductById(Long Id)
    {
        return null;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto product) {
        return null;
    }
}

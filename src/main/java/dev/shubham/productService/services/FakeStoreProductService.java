package dev.shubham.productService.services;


import dev.shubham.productService.Exceptions.NotFoundException;
import dev.shubham.productService.thirdpartyclients.productService.fakestore.FakeStoreProductDto;
import dev.shubham.productService.dtos.GenericProductDto;
import dev.shubham.productService.thirdpartyclients.productService.fakestore.FakeStoryProductServiceClient;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Primary
@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService {
    private FakeStoryProductServiceClient fakeStoryProductServiceClient;

    private GenericProductDto convertFakeStoreProductIntoGenericProduct(FakeStoreProductDto fakeStoreProductDto) {

        GenericProductDto product = new GenericProductDto();
        product.setId(fakeStoreProductDto.getId());
        product.setImage(fakeStoreProductDto.getImage());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setCategory(fakeStoreProductDto.getCategory());

        return product;
    }

    public FakeStoreProductService(FakeStoryProductServiceClient fakeStoryProductServiceClient) {
        this.fakeStoryProductServiceClient = fakeStoryProductServiceClient;
    }


    public GenericProductDto createProduct(GenericProductDto product) {
        return convertFakeStoreProductIntoGenericProduct(fakeStoryProductServiceClient.createProduct(product));
    }

    public GenericProductDto getProductById(Long id) throws NotFoundException {
        return convertFakeStoreProductIntoGenericProduct(fakeStoryProductServiceClient.getProductById(id));
    }

    public List<GenericProductDto> getAllProducts() {
        List<GenericProductDto> genericProductDtos = new ArrayList<>();

        for (FakeStoreProductDto fakeStoreProductDto: fakeStoryProductServiceClient.getAllProducts()) {
            genericProductDtos.add(convertFakeStoreProductIntoGenericProduct(fakeStoreProductDto));
        }
        return genericProductDtos;
    }

    public GenericProductDto deleteProduct(Long id) {
        return convertFakeStoreProductIntoGenericProduct(fakeStoryProductServiceClient.deleteProduct(id));
    }
}

package dev.shubham.productService.thirdpartyclients.productService.fakestore;

import dev.shubham.productService.Exceptions.NotFoundException;
import dev.shubham.productService.dtos.GenericProductDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class FakeStoryProductServiceClient {

    private RestTemplateBuilder restTemplateBuilder;
    private String specificProductRequestUrl="https://fakestoreapi.com/products/{id}";
    private  String productsRequestUrl="https://fakestoreapi.com/products";

    public FakeStoryProductServiceClient(RestTemplateBuilder restTemplateBuilder)
    {
        this.restTemplateBuilder=restTemplateBuilder;
    }


    public FakeStoreProductDto createProduct(GenericProductDto product){
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response=restTemplate.postForEntity(productsRequestUrl,product,FakeStoreProductDto.class);
        return  response.getBody();
    }

    public FakeStoreProductDto getProductById(Long id) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response =
                restTemplate.getForEntity(specificProductRequestUrl, FakeStoreProductDto.class, id);
        FakeStoreProductDto fakeStoreProductDto = response.getBody();

        if (fakeStoreProductDto == null) {
            throw new NotFoundException("Product with id: " + id + " doesn't exist.");
        }
         return fakeStoreProductDto;
    }

    public List<FakeStoreProductDto> getAllProducts() {
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> response= restTemplate.getForEntity(productsRequestUrl,FakeStoreProductDto[].class);
        return  Arrays.stream(response.getBody()).toList();
    }

    public FakeStoreProductDto deleteProduct(Long id) {
        RestTemplate restTemplate=restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor =
                restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> response = restTemplate.execute(specificProductRequestUrl, HttpMethod.DELETE, requestCallback, responseExtractor, id);
        return response.getBody();

    }
}

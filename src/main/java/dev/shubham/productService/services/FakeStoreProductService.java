package dev.shubham.productService.services;


import dev.shubham.productService.Exceptions.NotFoundException;
import dev.shubham.productService.dtos.ExceptionDto;
import dev.shubham.productService.dtos.FakeStoreProductDto;
import dev.shubham.productService.dtos.GenericProductDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements  ProductService {
  private String specificProductRequestUrl="https://fakestoreapi.com/products/{id}";
  private String createPostRequestUrl="https://fakestoreapi.com/products";
  private  String productsRequestUrl="https://fakestoreapi.com/products";

    private RestTemplateBuilder restTemplateBuilder;

    public  FakeStoreProductService(RestTemplateBuilder restTemplateBuilder)
    {
        this.restTemplateBuilder=restTemplateBuilder;
    }

    private GenericProductDto convertFakeStoreProductIntoGenericProduct(FakeStoreProductDto fakeStoreProductDto)
    {
        GenericProductDto product=new GenericProductDto();
        product.setId(fakeStoreProductDto.getId());
        product.setImage(fakeStoreProductDto.getImage());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setCategory(fakeStoreProductDto.getCategory());
        return product;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto product){
      RestTemplate restTemplate=restTemplateBuilder.build();
      ResponseEntity<GenericProductDto> response=restTemplate.postForEntity(createPostRequestUrl,product,GenericProductDto.class);
      return  response.getBody();
    }

    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException {
//        FakeStoreProductService fakeStoreProductService = new FakeStoreProductService();
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response =
                restTemplate.getForEntity(specificProductRequestUrl, FakeStoreProductDto.class, id);
        System.out.println("------>>>response "+response);


        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        System.out.println("------>>>fakeStoreProductDto "+fakeStoreProductDto);

        if (fakeStoreProductDto == null) {
            throw new NotFoundException("Product with id: " + id + " doesn't exist.");
        }

//        response.getStatusCode()

        return convertFakeStoreProductIntoGenericProduct(fakeStoreProductDto);
//        return null;
    }

  @Override
  public List<GenericProductDto> getProducts() {
      RestTemplate restTemplate=restTemplateBuilder.build();
       ResponseEntity<FakeStoreProductDto[]> response= restTemplate.getForEntity(productsRequestUrl,FakeStoreProductDto[].class);
      System.out.println("=========================.=");
        List<GenericProductDto> answer=new ArrayList<>();

       for(FakeStoreProductDto fakeStoreProductDto: Arrays.stream(response.getBody()).toList())
       {
         answer.add(this.convertFakeStoreProductIntoGenericProduct(fakeStoreProductDto));
       }
       return  answer;
  }

    @Override
    public GenericProductDto deleteProduct(Long id) {
        RestTemplate restTemplate=restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(GenericProductDto.class);
        ResponseExtractor<ResponseEntity<GenericProductDto>> responseExtractor =
                restTemplate.responseEntityExtractor(GenericProductDto.class);
        ResponseEntity<GenericProductDto> response = restTemplate.execute(specificProductRequestUrl, HttpMethod.DELETE, requestCallback, responseExtractor, id);
     return response.getBody();

    }

}

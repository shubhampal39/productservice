package dev.shubham.productService.contollers;

import dev.shubham.productService.dtos.GenericProductDto;
import dev.shubham.productService.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController()
@RequestMapping("/api/vi/products")
public class ProductController {
 private ProductService productService;

 public ProductController(@Qualifier("fakeStoreProductService") ProductService productService)
 {
    this.productService=productService;
 }

   @GetMapping
    public void getAllProducts(){}
    @GetMapping("{id}")
    public GenericProductDto getProductById(@PathVariable("id") Long id){
       return productService.getProductById(id);
    }
    @DeleteMapping("{id}")
    public void deleteProductById(){}
    @PostMapping
    public String createProduct(@RequestBody GenericProductDto product){
//     return productService.createProduct(product);
       System.out.println(product);
       return  "shubham"+product.getTitle();
    }
    @PutMapping("{id}")
    public void updateProductById(){}

}

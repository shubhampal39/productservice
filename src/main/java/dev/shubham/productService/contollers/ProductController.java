package dev.shubham.productService.contollers;

import dev.shubham.productService.dtos.GenericProductDto;
import dev.shubham.productService.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController()
@RequestMapping("/products")
public class ProductController {
 private ProductService productService;

 public ProductController(@Qualifier("fakeStoreProductService") ProductService productService)
 {
    this.productService=productService;
 }

    @GetMapping
    public List<GenericProductDto> getAllProducts() {
    return productService.getProducts();
   }
    @GetMapping("{id}")
    public GenericProductDto getProductById(@PathVariable("id") Long id){
       return productService.getProductById(id);
    }
    @DeleteMapping("{id}")
    public void deleteProductById(@PathVariable("id")Long id){
      productService.deleteProduct(id);
    }
    @PostMapping
    public GenericProductDto createProduct(@RequestBody GenericProductDto product){
     return productService.createProduct(product);
    }
    @PutMapping("{id}")
    public void updateProductById(){}

}

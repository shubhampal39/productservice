package dev.shubham.productService.dtos;

import dev.shubham.productService.models.Category;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private double price;
    private String category;
    private String Description;
    private String image;
}

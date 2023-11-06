package dev.shubham.productService.dtos;

import dev.shubham.productService.models.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericProductDto {
    private  String id;
    private String title;
    private String description;
    private String image;
    private String category;
    private double price;
}

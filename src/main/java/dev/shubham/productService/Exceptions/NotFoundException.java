package dev.shubham.productService.Exceptions;

public class NotFoundException extends Exception {

    public NotFoundException(String message) {
        super(message);
        System.out.println("------>>>"+message);
    }
}

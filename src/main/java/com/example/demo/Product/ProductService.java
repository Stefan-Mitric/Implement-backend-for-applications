package com.example.demo.Product;

import com.example.demo.Product.Product;
import com.example.demo.Product.ProductDto;
import com.example.demo.Product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    private Object productDto;

    public ProductDto getProduct(int id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return toProductDto(product);
    }

    public List<ProductDto> getProduct() {
        return productRepository
                .findAll()
                .stream().map(this::toProductDto)
                .toList();
    }

    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    private ProductDto toProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        ProductDto.setId(product.getId());
        ProductDto.setProducts(product.getProducts().stream().map(product -> {
            ProductDto productDto = new ProductDto();
            ProductDto.setId(product.getId());
            ProductDto.setName(product.getName());
            return productDto;
        }).toList());
        return productDto;
    }
}


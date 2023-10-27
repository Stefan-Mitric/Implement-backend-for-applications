package com.example.demo.Product;

import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

public class ProductDto {
    public static void setId(Integer id) {
    }
    public static void setName(String name) {
    }
    private int id;

    private List<Object> products = new ArrayList<Object>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Object> getProducts() {
        return products;
    }

    public void setProducts(List<Object> products) {
        this.products = products;
    }

    public void setProduct(List<Object> list) {
    }
}

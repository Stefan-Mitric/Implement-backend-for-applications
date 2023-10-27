package com.example.demo.Person;

import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

public class PersonDto {
    private int id;

    @NotBlank
    private String lastName;

    @NotBlank
    private String firstName;

    private List<Object> products = new ArrayList<Object>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

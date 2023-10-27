package com.example.demo.Product;

import com.example.demo.Product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public class ProductRepository {
    public interface ProductRepository extends JpaRepository<Product, Integer> {
        Optional<Product> findById(int id);

        Optional<Product> findByUserName(String userName);
    }
}

package com.example.demo.repository;

import com.example.demo.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void findByName() {
        Product product = new Product(1,"saas",4,1500);
        productRepository.save(product);

        Product ActualResult = productRepository.findByName("saas");
        System.out.println("hello from test case");
        assertEquals(ActualResult,product);
    }
}
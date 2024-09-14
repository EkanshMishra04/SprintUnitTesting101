package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProductServiceTest {

    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Test
    void saveProductTest() {
        Product p = new Product(1,"mobile",24,12500);
        when(productRepository.save(p)).thenReturn(p);
        assertEquals(p,productService.saveProduct(p));
    }

    @Test
    void saveAllProductTest() {
        List<Product> products = Arrays.asList(new Product(1,"mobile",34,1500),
                new Product(2,"mobile",34,1500));
        when(productRepository.saveAll(products)).thenReturn(products);
        assertEquals(productService.saveAllProduct(products),products);
    }

    @Test
    void findAllTest() {
        when(productRepository.findAll()).thenReturn(
                Stream.of(new Product(1,"mobile",34,1500),
                        new Product(2,"mobile",34,1500)).collect(Collectors.toList()));

        assertEquals(2,productService.findAll().size());
        System.out.println("working successfully broo");
    }

    @Test
    void findOneTest() {
        Product product = new Product(2,"mobile",34,1500);
        when(productRepository.findById(2)).thenReturn(Optional.of(product));
        assertEquals(2,productService.findOne(2).getId());
    }

    @Test
    void getByNameTest() {
        String name = "mobile";
        Product p = new Product(1,"mobile",24,12500);
        when(productRepository.findByName(name)).thenReturn(p);
        assertEquals(p,productService.getByName(name));
        assertEquals(p.getName(),productService.getByName(name).getName());
    }

    @Test
    void deleteByIdTest() {
        Product p = new Product(1,"mobile",24,12500);
        productService.deleteById(1);
        verify(productRepository,times(1)).deleteById(1);
    }

    @Test
    void updateProductTest() {
        Product existingProduct = new Product(1, "oldName", 20, 1000);
        Product updatedProduct = new Product(1, "newName", 30, 2000);
        // Define behavior for repository
        when(productRepository.findById(1)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(existingProduct)).thenReturn(updatedProduct);

        Product result = productService.updateProduct(updatedProduct);

        assertNotNull(result);
        assertEquals("newName", result.getName());
        assertEquals(30, result.getQuantity());
        assertEquals(2000, result.getPrice());

        // Verify interactions with repository
        verify(productRepository, times(1)).findById(1);
        verify(productRepository, times(1)).save(existingProduct);
    }
}
package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService serivce;

    @PostMapping("/addProduct")
    public Product saveProduct(@RequestBody Product product){
        return serivce.saveProduct(product);
    }

    @PostMapping("/addProducts")
    public List<Product> saveAllProduct(@RequestBody List<Product> products){
        return serivce.saveAllProduct(products);
    }

    @GetMapping("/products")
    public List<Product> findAllProducts(){
        return serivce.findAll();
    }

    @GetMapping("/productById/{id}")
    public Product findProductById(@PathVariable Integer id){
        return serivce.findOne(id);
    }

    @GetMapping("/productByName/{name}")
    public Product findProductByName(@PathVariable String name){
        return serivce.getByName(name);
    }

    @PutMapping ("/updateProduct")
    public Product updateProduct(@RequestBody Product product){
        return serivce.updateProduct(product);
    }

    @DeleteMapping("/product/{id}")
    public String deleteProduct(@PathVariable Integer id){
        return serivce.deleteById(id);
    }
}

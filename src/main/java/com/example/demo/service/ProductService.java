package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Product saveProduct(Product product){
       return repository.save(product);
    }

    public List<Product> saveAllProduct(List<Product> products){
        return repository.saveAll(products);
    }

    public List<Product> findAll(){
        return repository.findAll();
    }

    public Product findOne(Integer id){
        return repository.findById(id).orElse(null);
    }

    public Product getByName(String name){
        return repository.findByName(name);
    }

    public String deleteById(Integer id){
        repository.deleteById(id);
        return "deleted product with id: "+id;
    }

    public Product updateProduct(Product product){
        Product existingProduct = repository.findById(product.getId()).orElse(new Product());
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setQuantity(product.getQuantity());
        return repository.save(existingProduct);
    }



}

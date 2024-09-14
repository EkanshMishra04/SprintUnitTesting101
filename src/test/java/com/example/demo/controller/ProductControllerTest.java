package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.*;
@SpringBootTest
class ProductControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private ProductService productService;

    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void saveProduct() throws Exception {
        Product product = new Product();
        product.setName("mobile");
        product.setPrice(1500);
        product.setQuantity(5);

        Product savedProduct = new Product();
        savedProduct.setId(1);
        savedProduct.setName("mobile");
        savedProduct.setPrice(1500);
        savedProduct.setQuantity(5);

        when(productService.saveProduct(any(Product.class))).thenReturn(savedProduct);
        String productString = objectMapper.writeValueAsString(product);

        MvcResult result = mockMvc.perform(post("/addProduct")
                        .content(productString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // Assert
        String resultContent = result.getResponse().getContentAsString();
        Product resultProduct = objectMapper.readValue(resultContent, Product.class);

        assertNotNull(resultProduct);
        assertEquals(1, resultProduct.getId());
        assertEquals("mobile", resultProduct.getName());
        assertEquals(1500, resultProduct.getPrice());
        assertEquals(5, resultProduct.getQuantity());

        verify(productService, times(1)).saveProduct(any(Product.class));
    }

    @Test
    void saveAllProduct() throws Exception {
        // Arrange
        List<Product> products = Arrays.asList(
                new Product("mobile", 1500, 5),
                new Product("laptop", 3000, 2)
        );

        List<Product> savedProducts = Arrays.asList(
                new Product(1, "mobile", 1500, 5),
                new Product(2, "laptop", 3000, 2)
        );

        when(productService.saveAllProduct(anyList())).thenReturn(savedProducts);
        String productsString = objectMapper.writeValueAsString(products);

        // Act
        MvcResult result = mockMvc.perform(post("/addProducts")
                        .content(productsString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // Assert
        String resultContent = result.getResponse().getContentAsString();
        List<Product> resultProducts = objectMapper.readValue(resultContent, new TypeReference<List<Product>>() {});

        assertNotNull(resultProducts);
        assertEquals(2, resultProducts.size());
        assertEquals("mobile", resultProducts.get(0).getName());
        assertEquals("laptop", resultProducts.get(1).getName());

        verify(productService, times(1)).saveAllProduct(anyList());
    }

    @Test
    void findAllProducts() throws Exception {
        // Arrange
        List<Product> products = Arrays.asList(
                new Product(1, "mobile", 1500, 5),
                new Product(2, "laptop", 3000, 2)
        );

        when(productService.findAll()).thenReturn(products);

        // Act
        MvcResult result = mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andReturn();

        // Assert
        String resultContent = result.getResponse().getContentAsString();
        List<Product> resultProducts = objectMapper.readValue(resultContent, new TypeReference<List<Product>>() {});

        assertNotNull(resultProducts);
        assertEquals(2, resultProducts.size());
        assertEquals("mobile", resultProducts.get(0).getName());
        assertEquals("laptop", resultProducts.get(1).getName());

        verify(productService, times(1)).findAll();
    }

    @Test
    void findProductById() throws Exception {
        // Arrange
        Product product = new Product(1, "mobile", 5, 1500);

        when(productService.findOne(1)).thenReturn(product);

        // Act
        MvcResult result = mockMvc.perform(get("/productById/1"))
                .andExpect(status().isOk())
                .andReturn();

        // Assert
        String resultContent = result.getResponse().getContentAsString();
        Product resultProduct = objectMapper.readValue(resultContent, Product.class);

        assertNotNull(resultProduct);
        assertEquals(1, resultProduct.getId());
        assertEquals("mobile", resultProduct.getName());
        assertEquals(1500, resultProduct.getPrice());
        assertEquals(5, resultProduct.getQuantity());

        verify(productService, times(1)).findOne(1);
    }

    @Test
    void findProductByName() throws Exception {
        // Arrange
        Product product = new Product(1, "mobile", 5, 1500);

        when(productService.getByName("mobile")).thenReturn(product);

        // Act
        MvcResult result = mockMvc.perform(get("/productByName/mobile"))
                .andExpect(status().isOk())
                .andReturn();

        // Assert
        String resultContent = result.getResponse().getContentAsString();
        Product resultProduct = objectMapper.readValue(resultContent, Product.class);

        assertNotNull(resultProduct);
        assertEquals(1, resultProduct.getId());
        assertEquals("mobile", resultProduct.getName());
        assertEquals(1500, resultProduct.getPrice());
        assertEquals(5, resultProduct.getQuantity());

        verify(productService, times(1)).getByName("mobile");
    }

    @Test
    void updateProduct() throws Exception {
        // Arrange
        Product product = new Product(1, "mobile", 5, 1500);
        Product updatedProduct = new Product(1, "updatedMobile", 10, 2000);

        when(productService.updateProduct(any(Product.class))).thenReturn(updatedProduct);

        String productString = objectMapper.writeValueAsString(product);

        // Act
        MvcResult result = mockMvc.perform(put("/updateProduct")
                        .content(productString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // Assert
        String resultContent = result.getResponse().getContentAsString();
        Product resultProduct = objectMapper.readValue(resultContent, Product.class);

        assertNotNull(resultProduct);
        assertEquals(1, resultProduct.getId());
        assertEquals("updatedMobile", resultProduct.getName());
        assertEquals(2000, resultProduct.getPrice());
        assertEquals(10, resultProduct.getQuantity());

        verify(productService, times(1)).updateProduct(any(Product.class));
    }

    @Test
    void deleteProduct() throws Exception {
        // Arrange
        String expectedResponse = "deleted product with id: 1";

        // Assume the product is successfully deleted
        when(productService.deleteById(1)).thenReturn(expectedResponse);

        // Act
        MvcResult result = mockMvc.perform(delete("/product/1"))
                .andExpect(status().isOk())
                .andReturn();

        // Assert
        String resultContent = result.getResponse().getContentAsString();
        assertEquals(expectedResponse, resultContent);

        verify(productService, times(1)).deleteById(1);
    }
}
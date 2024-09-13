package com.example.warehouse.service;

import com.example.warehouse.customException.PriceShouldGreaterThanZero;
import com.example.warehouse.customException.ProductNotFoundException;
import com.example.warehouse.model.Product;
import com.example.warehouse.repo.WHJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WHService {

    @Autowired
    private WHJpa whJpa;

    public ResponseEntity<Product> addProduct(Product product) {
        if(product.getPrice() <= 0) throw new PriceShouldGreaterThanZero("Price should be greater than 0");
        Product savedProduct = whJpa.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products = whJpa.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    public ResponseEntity<Product> getProductById(int id) {
        Product product = whJpa.findAll().stream().filter(pro -> pro.getId() == id).findFirst().orElse(null);

        if(product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    public ResponseEntity<Product> updateProductById(int id, Product product) {

        Product product1 = whJpa.findAll().stream().filter(pro -> pro.getId() == id).findFirst()
                .orElseThrow(() -> new ProductNotFoundException("Exception Handler working... PRoduct not found"));

        product1.setName(product.getName());
        product1.setPrice(product.getPrice());
        product1.setQuantity(product.getQuantity());
        whJpa.save(product1);

        return ResponseEntity.status(HttpStatus.CREATED).body(product1);
    }

    public ResponseEntity<String> deleteProductById(int id) {
        whJpa.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Product with id " + id + " has been deleted..!");
    }
}

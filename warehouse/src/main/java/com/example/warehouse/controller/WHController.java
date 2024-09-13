package com.example.warehouse.controller;

import com.example.warehouse.model.Product;
import com.example.warehouse.service.WHService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/warehouse")
public class WHController {


    @Autowired
    private WHService whService;


    //Add Product(name, price, quantity)
    @PostMapping(path = "/addProduct")
    public ResponseEntity<Product> adddProduct(@RequestBody Product product) {
        return whService.addProduct(product);
    }

    //Get the all the products in Warehouse
    @GetMapping(path = "/getProducts")
    public ResponseEntity<List<Product>> getProducts() {
        return whService.getProducts();
    }

    //Get the Product by Id
    @GetMapping(path = "/getProductById")
    public ResponseEntity<Product> getProductById(@RequestParam int id) {
        return whService.getProductById(id);
    }

    // Update the product by id
    @PutMapping(path = "/updateProductById")
    public ResponseEntity<Product> updateProductById(@RequestParam int id, @RequestBody Product product) {
        return whService.updateProductById(id, product);
    }

    @DeleteMapping("/deleteProductById/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable int id) {
        return whService.deleteProductById(id);
    }
}

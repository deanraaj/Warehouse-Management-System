package com.example.warehouse.repo;

import com.example.warehouse.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WHJpa extends JpaRepository<Product, Integer> {


}

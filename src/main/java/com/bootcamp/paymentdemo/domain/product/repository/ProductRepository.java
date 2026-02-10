package com.bootcamp.paymentdemo.domain.product.repository;

import com.bootcamp.paymentdemo.domain.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository <Product,Long> {
    Optional<Product> findByProductIdAndDeletedFalse(Long productId);
}

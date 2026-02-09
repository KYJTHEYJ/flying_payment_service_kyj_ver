package com.bootcamp.paymentdemo.domain.product.service;

import com.bootcamp.paymentdemo.domain.product.dto.ProductGetResponse;
import com.bootcamp.paymentdemo.domain.product.entity.Product;
import com.bootcamp.paymentdemo.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    // 구현 : 상품 목록 조회, 상품 단건 조회

    @Transactional(readOnly = true)
    public List<ProductGetResponse> findAll() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(product -> new ProductGetResponse(
                        product.getId(),
                        product.getName(),
                        product.getDescription(),
                        product.getCategory(),
                        product.getPrice(),
                        product.getStock(),
                        product.getStatus(),
                        product.getCreatedAt(),
                        product.getUpdatedAt()
                )).toList();
    }

    @Transactional(readOnly = true)
    public ProductGetResponse findOne(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않은 상품입니다.")
        );
        return new ProductGetResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getCategory(),
                product.getPrice(),
                product.getStock(),
                product.getStatus(),
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }
}

package com.meretskiy.cloud.project.product.service.repositories;

import com.meretskiy.cloud.project.product.service.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}

package com.meretskiy.spring.data.homework.repositories;

import com.meretskiy.spring.data.homework.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findAllByCostGreaterThan(Integer minCost);

    List<Product> findAllByCostIsLessThan(Integer maxCost);

    List<Product> findAllByCostBetween(Integer min, Integer max);
}

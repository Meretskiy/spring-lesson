package com.flamexander.spring.db.data.repositories;

import com.flamexander.spring.db.data.dto.ProductDtoClassProjection;
import com.flamexander.spring.db.data.dto.ProductDtoInterfaceProjection;
import com.flamexander.spring.db.data.dto.ProductSimpleDto;
import com.flamexander.spring.db.data.entitites.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedQuery;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<ProductDtoInterfaceProjection> findAllBy();

    List<ProductDtoClassProjection> findAllByPriceGreaterThan(int minPrice);

//    @Query("select p.id as id, p.title as title, p.price as anotherPriceField from Product p")
    @Query("select new com.flamexander.spring.db.data.dto.ProductSimpleDto(p) from Product p")
    List<ProductSimpleDto> findAllProductsWithManualRequest();

    @Query(value = "select * from products", nativeQuery = true)
    List<Product> findAllByNativeSql();

    Product namedFindByTitle(String title);
}

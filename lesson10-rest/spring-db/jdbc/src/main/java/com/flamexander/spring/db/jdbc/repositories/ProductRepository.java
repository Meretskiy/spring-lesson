package com.flamexander.spring.db.jdbc.repositories;

import com.flamexander.spring.db.jdbc.model.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class  ProductRepository {
    private final JdbcTemplate jdbcTemplate;

    RowMapper<Product> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> new Product(resultSet.getLong("id"), resultSet.getString("title"), resultSet.getInt("price"));

    public List<Product> findAll() {
        return jdbcTemplate.query("select * from products", ROW_MAPPER);
    }

    public Product findOneById(Long id) {
        Product product = null;
        try {
            product = jdbcTemplate.queryForObject("select * from products where id = ?", ROW_MAPPER, id);
        } catch (DataAccessException e) {
            log.error("Couldn't find entity of type Product with id {}", id, e);
        }
        return product;
    }
}

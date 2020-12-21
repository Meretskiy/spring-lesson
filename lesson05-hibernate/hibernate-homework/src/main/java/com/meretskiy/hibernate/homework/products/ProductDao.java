package com.meretskiy.hibernate.homework.products;

import org.hibernate.Session;

import java.util.List;

public class ProductDao {

    public Product findById(Long id) {
        Product p;
        try (Session session = GlobalSessionFactory.get().getCurrentSession()) {
            session.beginTransaction();
            p = session.get(Product.class, id);
            session.getTransaction().commit();
        }
        return p;
    }

    public List<Product> findAll() {
        List<Product> productList;
        try (Session session = GlobalSessionFactory.get().getCurrentSession()) {
            session.beginTransaction();
            productList = session.createQuery("from Product ").getResultList();
            session.getTransaction().commit();
        }
        return productList;
    }

    public void deleteById(Long id) {
        try (Session session = GlobalSessionFactory.get().getCurrentSession()) {
            session.beginTransaction();
            Product p = session.get(Product.class, id);
            session.delete(p);
            session.getTransaction().commit();
        }
    }

    public Product saveOrUpdate(Product product) {
        try (Session session = GlobalSessionFactory.get().getCurrentSession()) {
            session.beginTransaction();
            session.saveOrUpdate(product);
            session.getTransaction().commit();
        }
        return product;
    }
}

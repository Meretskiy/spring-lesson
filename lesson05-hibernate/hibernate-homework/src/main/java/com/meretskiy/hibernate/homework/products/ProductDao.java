package com.meretskiy.hibernate.homework.products;

import com.meretskiy.hibernate.homework.PrepareDataApp;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ProductDao {
    private SessionFactory factory;

    public void init() {
        PrepareDataApp.forcePrepareData();
        factory = new Configuration().configure("configs/products/hibernate.cfg.xml").buildSessionFactory();
    }

    public void shutdown() {
        factory.close();
    }

    public Product findById(Long id) {
        Product p;
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            p = session.get(Product.class, id);
            session.getTransaction().commit();
        }
        return p;
    }

    public List<Product> findAll() {
        List<Product> productList;
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            productList = session.createQuery("from Product ").getResultList();
            session.getTransaction().commit();
        }
        return productList;
    }

    public void deleteById(Long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product p = session.get(Product.class, id);
            session.delete(p);
            session.getTransaction().commit();
        }
    }

    public Product saveOrUpdate(Product product) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.saveOrUpdate(product);
            session.getTransaction().commit();
        }
        return product;
    }
}

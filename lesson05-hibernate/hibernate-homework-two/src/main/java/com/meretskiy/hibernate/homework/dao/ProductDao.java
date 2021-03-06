package com.meretskiy.hibernate.homework.dao;

import com.meretskiy.hibernate.homework.model.Product;
import com.meretskiy.hibernate.homework.services.SessionFactoryService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDao implements Dao{

    private SessionFactoryService sessionFactory;

    @Autowired
    public ProductDao(SessionFactoryService sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Product findById(Long id) {
        Product p;
        try (Session session = sessionFactory.get().getCurrentSession()) {
            session.beginTransaction();
            p = (Product) session.getNamedQuery("productWithOrders")
                    .setParameter("id", id)
                    .getSingleResult();
            session.getTransaction().commit();
        }
        return p;
    }

    public Product saveOrUpdate(Product product) {
        try (Session session = sessionFactory.get().getCurrentSession()) {
            session.beginTransaction();
            session.saveOrUpdate(product);
            session.getTransaction().commit();
        }
        return product;
    }

    public List<Product> findAll() {
        List<Product> productList;
        try (Session session = sessionFactory.get().getCurrentSession()) {
            session.beginTransaction();
            productList = session.createNamedQuery("productWithOrdersAll").getResultList();
            session.getTransaction().commit();
        }
        return productList;
    }

    public void deleteById(Long id) {
        try (Session session = sessionFactory.get().getCurrentSession()) {
            session.beginTransaction();
            Product p = session.get(Product.class, id);
            session.delete(p);
            session.getTransaction().commit();
        }
    }
}

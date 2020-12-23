package com.meretskiy.hibernate.homework.services;

import com.meretskiy.hibernate.homework.model.Buyer;
import com.meretskiy.hibernate.homework.model.Order;
import com.meretskiy.hibernate.homework.model.Product;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    private SessionFactoryService sessionFactoryService;

    @Autowired
    public OrderService(SessionFactoryService sessionFactoryService) {
        this.sessionFactoryService = sessionFactoryService;
    }

    public List<Order> findOrdersByBuyerId(Long id) {
        List<Order> orderList;
        try (Session session = sessionFactoryService.get().getCurrentSession()) {
            session.beginTransaction();
            orderList = session.getNamedQuery("withBuyer")
                    .setParameter("id", id).getResultList();

            session.getTransaction().commit();
        }
        return orderList;
    }

    public List<Buyer> findBuyersByProductId(Long id) {
        List<Buyer> buyerList;
        try (Session session = sessionFactoryService.get().getCurrentSession()) {
            session.beginTransaction();
            buyerList = session.getNamedQuery("withProduct")
                    .setParameter("id", id).getResultList();
            session.getTransaction().commit();
        }
        return buyerList;
    }

    public List<Order> findAll() {
        List<Order> orderList;
        try (Session session = sessionFactoryService.get().getCurrentSession()) {
            session.beginTransaction();
            orderList = session.createQuery("from Order ").getResultList();
            session.getTransaction().commit();
        }
        return orderList;
    }

    public void addOrder(Long order_id, Long buyer_id, Long product_id) {
        Date date = new Date();

        try (Session session = sessionFactoryService.get().getCurrentSession()) {
            session.beginTransaction();
            session.save(new Order(
                    order_id,
                    session.get(Buyer.class, buyer_id),
                    session.get(Product.class, product_id),
                    date,
                    session.get(Product.class, product_id).getCost()));
            session.getTransaction().commit();
        }
    }
}

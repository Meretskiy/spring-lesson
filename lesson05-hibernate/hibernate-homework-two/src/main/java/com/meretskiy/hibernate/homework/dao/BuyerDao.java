package com.meretskiy.hibernate.homework.dao;


import com.meretskiy.hibernate.homework.model.Buyer;
import com.meretskiy.hibernate.homework.services.SessionFactoryService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BuyerDao implements Dao{

    SessionFactoryService sessionFactory;

    @Autowired
    public BuyerDao(SessionFactoryService sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Buyer findById(Long id) {
        Buyer b;
        try (Session session = sessionFactory.get().getCurrentSession()) {
            session.beginTransaction();
            b = (Buyer) session.createNamedQuery("buyerWithOrders")
                    .setParameter("id", id)
                    .getSingleResult();
            session.getTransaction().commit();
        }
        return b;
    }

    public Buyer saveOrUpdate(Buyer buyer) {
        try (Session session = sessionFactory.get().getCurrentSession()) {
            session.beginTransaction();
            session.saveOrUpdate(buyer);
            session.getTransaction().commit();
        }
        return buyer;
    }

    public List<Buyer> findAll() {
        List<Buyer> buyerList;
        try (Session session = sessionFactory.get().getCurrentSession()){
            session.beginTransaction();
            buyerList = session.createNamedQuery("buyerWithOrdersAll").getResultList();
            session.getTransaction().commit();
        }
        return buyerList;
    }

    public void deleteById(Long id) {
        try (Session session = sessionFactory.get().getCurrentSession()) {
            session.beginTransaction();
            Buyer buyer = session.get(Buyer.class, id);
            session.delete(buyer);
            session.getTransaction().commit();
        }
    }
}

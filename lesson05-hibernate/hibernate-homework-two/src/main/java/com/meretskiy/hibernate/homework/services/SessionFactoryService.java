package com.meretskiy.hibernate.homework.services;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName = "singleton")
public class SessionFactoryService {

    private SessionFactory sessionFactory;

    @Autowired
    public SessionFactoryService() {
        this.sessionFactory = new Configuration()
                .configure("configs/hibernate.cfg.xml").buildSessionFactory();
    }

    public SessionFactory get() {
        return sessionFactory;
    }

    public void close() {
        sessionFactory.close();
    }
}

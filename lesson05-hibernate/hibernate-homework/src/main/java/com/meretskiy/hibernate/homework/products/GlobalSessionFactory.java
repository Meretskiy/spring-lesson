package com.meretskiy.hibernate.homework.products;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GlobalSessionFactory {

    private static SessionFactory sessionFactory = new Configuration()
            .configure("configs/products/hibernate.cfg.xml")
            .buildSessionFactory();

    public static SessionFactory get() {
        return sessionFactory;
    }

    public static void close() {
        sessionFactory.close();
    }
}

package com.meretskiy.hibernate.lesson.many_to_many;

import com.meretskiy.hibernate.lesson.PrepareDataApp;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ManyToManyApp {
    public static void main(String[] args) {
        PrepareDataApp.forcePrepareData();

        SessionFactory factory = new Configuration()
                .configure("configs/many_to_many/hibernate.cfg.xml")
                .buildSessionFactory();

        Session session = null;
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            //достаем первого читателя
            Reader reader = session.get(Reader.class, 1L);
            System.out.println(reader);
            System.out.println("Books: ");
            //печатаем все книги которые он прочитал
            for (Book b : reader.getBooks()) {
                System.out.println(b.getTitle());
            }

            //HQL
            List<Reader> readers =
                    //формируем лист читателей из таблицы читателей и сортируем по количеству прочитанных книг
                    session.createQuery("SELECT r FROM Reader r ORDER BY size(r.books) DESC ").getResultList();
                    //удаляем книгу с id = 1
//                  session.createQuery("DELETE  FROM Book b WHERE b.id = 1").executeUpdate();
                    //из книг достать только title с id 1 и онвернет не книгу а string title
//                  session.createQuery("SELECT b.title FROM Book b WHERE b.id = 1", String.class).getSingleResult();
            System.out.println(readers);
            session.getTransaction().commit();

            session = factory.getCurrentSession();
            session.beginTransaction();
            //складываем в лист все книги из таблицы
            List<Book> allBooks = session.createQuery("SELECT b FROM Book b").getResultList();
            session.getTransaction().commit();
        } finally {
            factory.close();
            if (session != null) {
                session.close();
            }
        }
    }
}


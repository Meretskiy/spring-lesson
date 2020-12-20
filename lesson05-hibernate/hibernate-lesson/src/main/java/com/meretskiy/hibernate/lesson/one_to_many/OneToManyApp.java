package com.meretskiy.hibernate.lesson.one_to_many;

import com.meretskiy.hibernate.lesson.PrepareDataApp;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class OneToManyApp {
    public static void main(String[] args) {
        PrepareDataApp.forcePrepareData();

        SessionFactory factory = new Configuration().configure("configs/one_to_many/hibernate.cfg.xml")
                .buildSessionFactory();
        Session session = null;
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();

            University university = session.get(University.class, 1L);

            System.out.println(university);
            System.out.println("Students: ");
            for (Student s : university.getStudents()) {
                System.out.println(s.getName());
            }

            Student student = new Student("Billy", university);
            session.save(student);

            session.getTransaction().commit();

            session = factory.getCurrentSession();

            //пример вызова именнованного запроса:
//            session.beginTransaction();
//            University universityFetch = (University)session.getNamedQuery("withStudents")
//                    .setParameter("id", 2L)
//                    .getSingleResult();
//            session.getTransaction().commit();
//            System.out.println(universityFetch.getStudents());
        } finally {
            factory.close();
            if (session != null) {
                session.close();
            }
        }
    }
}

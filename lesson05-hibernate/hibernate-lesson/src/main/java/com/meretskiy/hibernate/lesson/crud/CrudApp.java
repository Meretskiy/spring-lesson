package com.meretskiy.hibernate.lesson.crud;

import com.meretskiy.hibernate.lesson.PrepareDataApp;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CrudApp {
    //фабрика сессий, позволяющая строить сессии
    private static SessionFactory factory;

    //Сессия - это еденица работы с базой данных.
    //если мы хотим отправить какой либо запрос, это действие должно выполняться в рамках сессии.
    //private static Session session;

    /**
    подготовка базы, запуск hibernate, создание фабрики сессий
     */
    public static void init() {
        //подготовка базы
        //запускаем hibernate, подключаемся к базе, чистм все таблицы, создаем новые и заливаем стартовые данные
        PrepareDataApp.forcePrepareData();

        //начало работы с самим hibernate. Создаем фабрику сессий. Тяжелый большой объект для того чтобы мы могли
        //работать с hibernate, создавать сессии. Обычно создается только один на проект.
        factory = new Configuration()         //создаем конфигурацию
                .configure("configs/crud/hibernate.cfg.xml") //из файла
                .buildSessionFactory();                      //и сторим session factory
    }

    /**
     * закрытие фабрики
     */
    public static void shutdown() {
        factory.close();
    }

    public static void main(String[] args) {
        try {
            init();
            createExample();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            shutdown();
        }
    }

    /**
     * CREATE
     */
    public static void createExample() {
        //запрашиваем у фабрики сессию в try-catch чтобы закрылась автоматом.
        try (Session session = factory.getCurrentSession()) {
            //Любые операции в БД выполняются в рамках транзакций, открываем транзакцию.
            session.beginTransaction();
            //Создаем объект для отправки в бд
            SimpleItem dragonStatue = new SimpleItem("Dragon Statue", 100);
            System.out.println(dragonStatue);
            //для сохранения обращаемся к сесси и делаем либо saveOrUpdate либо Save
            session.save(dragonStatue);
            System.out.println(dragonStatue);
            //Коммитим нашу транзакцию что бы наша операция была выполнена в БД
            session.getTransaction().commit();
            System.out.println(dragonStatue);
        }
    }
}

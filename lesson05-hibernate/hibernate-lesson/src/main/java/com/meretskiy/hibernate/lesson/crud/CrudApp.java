package com.meretskiy.hibernate.lesson.crud;

import com.meretskiy.hibernate.lesson.PrepareDataApp;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class CrudApp {
    //фабрика сессий, позволяющая строить сессии
    private static SessionFactory factory;

    //Сессия - это еденица работы с базой данных.
    //Это легковесный объект, который должен выполняться для каждой операции.
    //Если мы хотим отправить какой либо запрос, это действие должно выполняться в рамках сессии.
    //private static Session session;

    /**
     * подготовка базы, запуск hibernate, создание фабрики сессий
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
//            createExample();
//            readAndPrintExample();
//            updateExample();
//            deleteExample();
            showManyItems();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            shutdown();
        }
    }

    /**
     * CREATE(INSERT)
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

    /**
     * READ (SELECT)
     */
    public static void readAndPrintExample() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            //вытаскиваем итем из базы - берем сессию, указываем класс и id
            SimpleItem simpleItem = session.get(SimpleItem.class, 1L);
            //просто отдаем на печать или проталкиваем дальше куда нам нужно
            System.out.println(simpleItem);
            session.getTransaction().commit();
        }
    }

    /**
     * UPDATE
     */
    public static void updateExample() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            //вытаскиваем объект из базы
            SimpleItem simpleItem = session.get(SimpleItem.class, 1L);
            //меняем данные
            simpleItem.setPrice(10_000);
            session.getTransaction().commit();
            System.out.println(simpleItem);
        }
    }

    /**
     * DELETE
     */
    public static void deleteExample() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            //вытаскиваем объект из базы
            SimpleItem simpleItem = session.get(SimpleItem.class, 1L);
            //удаляем объект
            session.delete(simpleItem);
            session.getTransaction().commit();
        }
    }

    /**
     * HQL request
     */
    public static void showManyItems() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            //можно отправлять HQL запросы(объектные запросы)
            //в лист вычитать все объекты сущности
            List<SimpleItem> items = session.createQuery("from SimpleItem ").getResultList();
            System.out.println(items);
            //select с условием
            SimpleItem si1 = session.createQuery("select s from SimpleItem s where " +
                    "s.id = 3", SimpleItem.class).getSingleResult();
            System.out.println(si1);
            //select с ограничением
            List<SimpleItem> cheapItems = session.createQuery("select s from SimpleItem s where " +
                    "s.price < 80").getResultList();
            System.out.println(cheapItems);
            session.getTransaction().commit();
        }
    }
}

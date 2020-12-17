package com.meretskiy.spring.mvc;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import java.net.URL;
import java.security.ProtectionDomain;

/**
 * 1. Создать класс Товар (Product), с полями id , title , cost ;
 * 2. Товары необходимо хранить в репозитории (класс, в котором в виде List<Product> х ранятся
 * товары).
 * 3. Репозиторий должен уметь выдавать список всех товаров и товар по id;
 * 4. Сделать форму для добавления товара в репозиторий и логику работы этой формы;
 * 5. Сделать страницу, на которой отображаются все товары из репозитория.
 */
/*
Ручное развертывание Jetty сервера в spring mvc
 */
public class Launcher {
    public static void main(String[] args) throws Exception {
        //создаем jetty сервер
        Server server = new Server(8188);
        //магия из документации
        ProtectionDomain domain = Launcher.class.getProtectionDomain();
        URL location = domain.getCodeSource().getLocation();
        //Контекст с контекст пассом
        WebAppContext webAppContext = new WebAppContext();
        webAppContext.setContextPath("/app");
        //Указываем куда деплоить вар
        webAppContext.setWar(location.toExternalForm());
        //задаем контекст(отдаем серверу наш контекст)
        server.setHandler(webAppContext);
        //запускаем сервак и джойним, чтобы приложение не остановилось и сервак перехватывал наши запросы
        server.start();
        server.join();
    }
}

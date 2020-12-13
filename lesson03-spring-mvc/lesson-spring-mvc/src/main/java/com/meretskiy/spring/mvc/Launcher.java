package com.meretskiy.spring.mvc;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import java.net.URL;
import java.security.ProtectionDomain;

/*
Ручное развертывание Jetty сервера в spring mvc
 */
public class Launcher {
    public static void main(String[] args) throws Exception {
        //создаем jetty сервер
        Server server = new Server(8181);
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

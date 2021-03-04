package com.flamexander.spring.ws.configs;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

//Включаем веб сервисы и наследуем наш конфиг от WsConfigurerAdapter
@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {
    //настраиваем сервлет для SOAP сервисов
    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        //создаем сервлет, подвязываем контекст который заинжектили и указываем, что обработка наших SOAP запросов будет
        //идти по адресу /ws/*
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/ws/*");
    }

    //создаем описание, для пользователей нашим сервисом. Если клиент отправит запрос по эндпоинту он получит
    //полное описание контракта нашего сервиса.

    // http://localhost:8080/ws/groups.wsdl
    @Bean(name = "groups")
    public DefaultWsdl11Definition groupsWsdl11Definition(XsdSchema groupsSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("GroupsPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://www.flamexander.com/spring/ws/groups");
        wsdl11Definition.setSchema(groupsSchema);
        return wsdl11Definition;
    }

    //генерим сам wsdl файл
    // http://localhost:8080/ws/students.wsdl
    @Bean(name = "students")
    public DefaultWsdl11Definition studentsWsdl11Definition(XsdSchema studentsSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        //указываем что это студенты
        wsdl11Definition.setPortTypeName("StudentsPort");
        //путь для обработки веб сервисов(где искать wsdl файл)
        wsdl11Definition.setLocationUri("/ws");
        //указываем namespace нашей схемы
        wsdl11Definition.setTargetNamespace("http://www.flamexander.com/spring/ws/students");
        //подключаем нашу схему
        wsdl11Definition.setSchema(studentsSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema groupsSchema() {
        return new SimpleXsdSchema(new ClassPathResource("groups.xsd"));
    }

    //создаем схему описание студентов из xsd для генерации wsdl файла для пользователей нашего сервиса
    @Bean
    public XsdSchema studentsSchema() {
        return new SimpleXsdSchema(new ClassPathResource("students.xsd"));
    }
}

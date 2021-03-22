package com.flamexander.rabbitmq.console.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Общение на прямую, в обход Exchanger
 */
public class SimpleSenderApp {
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();                                //создаем фабрику соединений
        factory.setHost("localhost");                                                       //указываем хост
        try (Connection connection = factory.newConnection();                               //открываем соединение
             Channel channel = connection.createChannel()) {                                //открываем канал
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);    //объявляем очередь
            String message = "Hello World!";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes()); //отправляем сообщение в очередь
            System.out.println(" [x] Sent '" + message + "'");
        }
    }
}

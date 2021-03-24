package com.meretskiy.rabbitmq.project.blog.producer;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class Blog {
    private static final String EXCHANGE_NAME = "blogDirectExchanger";
    private static final String HOST_NAME = "localhost";
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST_NAME);

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

            while (true) {
                System.out.println("Write topic and article, or write exit");
                String str = sc.nextLine();
                if (str.equals("exit")) {
                    break;
                }
                String[] arr = str.split(" ", 2);
                String topic = arr[0];
                String article = arr[1];

                channel.basicPublish(EXCHANGE_NAME, topic, null, article.getBytes("UTF-8"));
                System.out.println("Article published");
            }
        }
    }
}


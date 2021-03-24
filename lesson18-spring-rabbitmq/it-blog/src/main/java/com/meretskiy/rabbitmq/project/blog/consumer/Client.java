package com.meretskiy.rabbitmq.project.blog.consumer;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class Client {

    private static final String EXCHANGE_NAME = "blogDirectExchanger";
    private static final String HOST_NAME = "localhost";
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST_NAME);
        try (Connection connection = factory.newConnection();
            Channel channel = connection.createChannel()) {
            String queueName = channel.queueDeclare().getQueue();

            System.out.println("Write: set_topic [topic name] to subscribe to articles of this topic.");
            System.out.println("Write: remove_topic [topic name] to unsubscribe to articles of this topic.");
            System.out.println("Write: exit - to end.");

            while (true) {
                String str = sc.nextLine();
                if (str.equals("exit")) {
                    break;
                }
                String[] arr = str.split(" ", 2);
                String command = arr[0];
                String topic = arr[1];

                if (command.equals("remove_topic")) {
                    channel.queueUnbind(queueName, EXCHANGE_NAME, topic);
                    System.out.println(topic + " unsubscribe.");
                }

                if (command.equals("set_topic")) {
                    channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
                    channel.queueBind(queueName, EXCHANGE_NAME, topic);
                    System.out.println("You are subscribed to " + topic + " topic awaiting article");
                }

                DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                    String message = new String(delivery.getBody(), "UTF-8");
                    System.out.println("Topic: " + topic + " Received article: '" + message + "'");
                };

                channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });
            }
        }
    }
}
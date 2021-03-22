package com.flamexander.rabbitmq.console.consumer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class TaskReceiverApp {
    private static final String TASK_QUEUE_NAME = "task_queue1";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        final Connection connection = factory.newConnection();
        final Channel channel = connection.createChannel();

        channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);
        System.out.println(" [*] Waiting for messages");

        // ограничение на кеширование задач одним consumerom (если не включить ограничение первый краулер может взять
        // все задачи на себя а остальные будут простаивать)
        channel.basicQos(3);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");

            System.out.println(" [x] Received '" + message + "'");
//            if (1 < 10) {
//                throw new RuntimeException("Oops");
//            }
            doWork(message);
            System.out.println(" [x] Done");

            //подтверждение обработки
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        };
        // AutoAck false - если вытащили из очереди посылку не факт что я ее обработаю. Только после подтверждения
        // обработки посылка удаляется из очереди.
        channel.basicConsume(TASK_QUEUE_NAME, false, deliverCallback, consumerTag -> {
        });
    }

    private static void doWork(String task) {
        for (char ch : task.toCharArray()) {
            if (ch == '.') {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException _ignored) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}

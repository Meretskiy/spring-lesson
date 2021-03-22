package com.flamexander.rabbitmq.spring;

import org.springframework.stereotype.Component;

@Component
public class SimpleMessageReceiver {
    public void receiveMessage(byte[] message) {
        System.out.println("Received from topic <" + new String(message) + ">");
    }
}
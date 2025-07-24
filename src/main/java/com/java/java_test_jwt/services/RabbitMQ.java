package com.java.java_test_jwt.services;

import com.java.java_test_jwt.utils.Utils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMQ {
    Utils utils = new Utils();

    public void addQueue(String queueName, String message) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(utils.returnRabbitMQPath());
        try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel())
        {
             channel.queueDeclare(queueName, false, false, false, null);
             channel.basicPublish("", queueName, null, message.getBytes());
        }
    }
}
package com.tony.publishsubscribe;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Created by hansongtao on 14-12-11.
 */
public class EmitLog {
    public static final String EXCHANGE_NAME = "logs";

    public static void main(String[] argv) throws java.io.IOException, InterruptedException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        String message = "Hello...";
        for (int i = 0; i < 20; i++) {
            channel.basicPublish(EXCHANGE_NAME, "", null, String.valueOf(i+1).concat(message).getBytes());
            System.out.println(" [x] Sent '" + (i+1)+message + "'");
        }

        Thread.sleep(10000);
        channel.close();
        connection.close();
    }


}

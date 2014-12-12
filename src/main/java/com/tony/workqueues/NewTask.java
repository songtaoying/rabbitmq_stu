package com.tony.workqueues;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

/**
 * Created by hansongtao on 14-12-11.
 */
public class NewTask {
    private final static String QUEUE_NAME = "task_queue";

    public static void main(String[] argv) throws java.io.IOException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
//        String message = getMessage(argv);
        String message = "Hello...";
        for (int i = 0; i < 20; i++) {
            channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, String.valueOf(i+1).concat(message).getBytes());
            System.out.println(" [x] Sent '" + (i+1)+message + "'");
        }
//        int prefetchCount = 1;
//        channel.basicQos(prefetchCount);


        channel.close();
        connection.close();
    }

//    private static String getMessage(String[] strings) {
//        if (strings.length < 1)
//            return "Hello World!";
//        return joinStrings(strings, " ");
//    }

    private static String joinStrings(String[] strings, String delimiter) {
        int length = strings.length;
        if (length == 0) return "";
        StringBuilder words = new StringBuilder(strings[0]);
        for (int i = 1; i < length; i++) {
            words.append(delimiter).append(strings[i]);
        }
        return words.toString();
    }
}

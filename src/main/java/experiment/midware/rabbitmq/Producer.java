package experiment.midware.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author : liulei
 **/
public class Producer {
    private final static String QUEUE_NAME = "Hello";

    public static void main(String[] argv) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("10.1.133.139");
        factory.setUsername("tom");
        factory.setPassword("123456");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = "DoSomething World!";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + message + "'");
        }


//        RabbitChannelPool pool = RabbitPoolInit.getPool();
//        Channel channel = pool.getChannel();
//
//        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
//        String message = "DoSomething World!";
//        channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
//        System.out.println(" [x] Sent '" + message + "'");
//
//        pool.returnChannel(channel);

    }
}

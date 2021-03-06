package experiment.midware.rabbitmq;

import com.rabbitmq.client.*;

/**
 * @author : liulei
 **/
public class Consumer {
    private final static String QUEUE_NAME = "Hello1";

    public static void main(String[] argv) throws Exception {

//        RabbitChannelPool pool = RabbitPoolInit.getPool();
////        Channel channel = pool.getChannel();

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("tom");
        factory.setPassword("123456");
        factory.setPort(5672);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("fanoutTest", BuiltinExchangeType.FANOUT);
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        channel.queueBind(QUEUE_NAME, "fanoutTest", "");
        System.out.println("consumer1 [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            String messageId = delivery.getProperties().getMessageId();
            System.out.println("consumer1 [x] Received '" + message + "'" + " messageId: " + messageId);
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
    }

}

package experiment.midware.rabbitmq;

import com.rabbitmq.client.*;

/**
 * @author : liulei
 **/
public class Consumer3 {
    private final static String QUEUE_NAME = "Hello3";

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
        System.out.println("consumer3 [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println("consumer3 [x] Received '" + message + "'");
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });

//        Thread.sleep(30000);
//        channel.close();
//        connection.close();
    }

}

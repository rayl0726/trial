package experiment.midware.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

/**
 * @author : liulei
 **/
public class Worker2 {
    private static final String TASK_QUEUE_NAME = "task_queue";

    public static void main(String[] argv) throws Exception {
        RabbitChannelPool pool = RabbitPoolInit.getPool();
        Channel channel = pool.getChannel();

        channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);
        System.out.println(" [worker2] Waiting for messages. To exit press CTRL+C");

        channel.basicQos(1);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");

            System.out.println(" [worker2] Received '" + message + "'");
            try {
                doWork(message);
            } finally {
                System.out.println(" [worker2] Done");
//                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            }
        };
        channel.basicConsume(TASK_QUEUE_NAME, true, deliverCallback, consumerTag -> { });
    }

    private static void doWork(String task) {
        for (char ch : task.toCharArray()) {
            if (ch == '.') {
                try {
                    Thread.sleep(2000);
                    System.out.println("[worker2] dowork");
                } catch (InterruptedException _ignored) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}

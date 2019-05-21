package experiment.midware.rabbitmq;

import com.rabbitmq.client.*;
import com.rabbitmq.client.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author : liulei
 **/
public class ReceiveLogs1 {

    private static final Logger logger = LoggerFactory.getLogger(ReceiveLogs1.class);
    private static final String EXCHANGE_NAME = "logs";
    private static final String QUEUE_NAME = "receive1";

    public static void main(String[] argv) throws Exception {
        RabbitChannelPool pool = RabbitPoolInit.getPool();
        Channel channel = pool.getChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        String queueName = channel.queueDeclare(QUEUE_NAME, true, false, false, null).getQueue();
        channel.queueBind(queueName, EXCHANGE_NAME, "");
        channel.basicQos(5000);
        logger.info(" [Receive1] Waiting for messages. To exit press CTRL+C");
        channel.basicConsume(queueName, false, newConsumer(channel));
    }


    public static Consumer newConsumer(Channel channel) {
        Consumer consumer = new Consumer() {
            @Override
            public void handleConsumeOk(String consumerTag) {
            }

            @Override
            public void handleCancelOk(String consumerTag) {
            }

            @Override
            public void handleCancel(String consumerTag) throws IOException {
            }

            @Override
            public void handleShutdownSignal(String consumerTag, ShutdownSignalException sig) {
            }

            @Override
            public void handleRecoverOk(String consumerTag) {
            }

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                try{
                    Thread.sleep(100);
                    String message = new String(body, "UTF-8");
                    logger.info(" [Receive1] Received '" + message + "'");
                } catch (Exception e) {
                    logger.error("[Receive1] Handle message error : {}", e.getMessage());
                } finally {
                    logger.info("[Receive1] Done");
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
            }
        };

        return consumer;
    }
}

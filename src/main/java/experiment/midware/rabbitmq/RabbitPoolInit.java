package experiment.midware.rabbitmq;

import com.rabbitmq.client.ConnectionFactory;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * @author : liulei
 **/
public class RabbitPoolInit {
    private final static String HOST = "10.1.133.139";
    private final static int PORT = 5672;
    private final static String USERNAME = "tom";
    private final static String PASSWORD = "123456";
    private static RabbitChannelPool pool;

    static {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST);
        factory.setPort(PORT);
        factory.setUsername(USERNAME);
        factory.setPassword(PASSWORD);
        pool = new RabbitChannelPool(factory, new GenericObjectPoolConfig());
    }

    public static RabbitChannelPool getPool() {
        return pool;
    }
}

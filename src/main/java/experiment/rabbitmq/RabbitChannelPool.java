package experiment.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author : liulei
 **/
public class RabbitChannelPool {

    private GenericObjectPool<Channel> pool;

    public RabbitChannelPool(ConnectionFactory factory, GenericObjectPoolConfig config) {
        this.pool = new GenericObjectPool<>(new RabbitChannelPoolFactory(factory), config);
    }


    public Channel getChannel() {
        try {
            return pool.borrowObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void returnChannel(Channel channel) {
        if(channel != null) {
            pool.returnObject(channel);
        }
    }

    public void closeChannel(Channel channel) {
        if(channel != null) {
            try {
                channel.close();
            } catch (IOException | TimeoutException e) {
                e.printStackTrace();
            }
        }
    }
}

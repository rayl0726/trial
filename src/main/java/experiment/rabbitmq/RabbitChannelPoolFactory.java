package experiment.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 * @author : liulei
 **/
public class RabbitChannelPoolFactory implements PooledObjectFactory<Channel> {

    private ConnectionFactory factory;

    public RabbitChannelPoolFactory(ConnectionFactory factory) {
        this.factory = factory;
    }

    @Override
    public PooledObject<Channel> makeObject() throws Exception {
        Channel channel = factory.newConnection().createChannel();
        return new DefaultPooledObject<>(channel);
    }

    @Override
    public void destroyObject(PooledObject<Channel> pooledObject) throws Exception {
        if(pooledObject != null) {
            pooledObject.getObject().close();
        }
    }

    @Override
    public boolean validateObject(PooledObject<Channel> pooledObject) {
        return pooledObject.getObject().isOpen();
    }

    @Override
    public void activateObject(PooledObject<Channel> pooledObject) throws Exception {

    }

    @Override
    public void passivateObject(PooledObject<Channel> pooledObject) throws Exception {

    }
}

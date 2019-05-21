package experiment.midware.zookeeper;

import org.apache.zookeeper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Random;

/**
 * @author : liulei
 **/
public class Worker implements Watcher {

    private static Logger logger = LoggerFactory.getLogger(Worker.class);

    ZooKeeper zooKeeper;
    String hostPort;
    Random random = new Random();
    String serverId = Integer.toHexString(random.nextInt());

    Worker(String hostPort) {
        this.hostPort = hostPort;
    }

    void startZK() throws IOException {
        zooKeeper = new ZooKeeper(hostPort, 10000, this);
    }


    @Override
    public void process(WatchedEvent watchedEvent) {
        logger.info(watchedEvent.toString() + "," + hostPort);
    }

    void register() {
        zooKeeper.create("/workers/worker-" + serverId, "Idle".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL, createWorkerCallback, null);
    }


    AsyncCallback.StringCallback createWorkerCallback = new AsyncCallback.StringCallback() {
        @Override
        public void processResult(int i, String s, Object o, String s1) {
            switch(KeeperException.Code.get(i)) {
                case CONNECTIONLOSS:
                    register();
                    break;
                case OK:
                    logger.info("Registered successfully: " + serverId);
                    break;
                case NODEEXISTS:
                    logger.warn("Already registered: " + serverId);
                    break;
                default:
                    logger.error("Something does wrong:" + KeeperException.create(KeeperException.Code.get(i), s));
            }
        }
    };

    public static void main(String[] args) throws Exception {
        Worker worker = new Worker(args[0]);
        worker.startZK();
        worker.register();

        Thread.sleep(30000);
    }
}

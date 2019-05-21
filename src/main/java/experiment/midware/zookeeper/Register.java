package experiment.midware.zookeeper;

import org.apache.zookeeper.*;

/**
 * @author : liulei
 **/
public class Register implements Watcher {

    private ZooKeeper zk;

    private static final String PATH = "/service";
    public Register(String zkServers) {
        try {
            zk = new ZooKeeper(zkServers, 20000, this);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public void RegisterImpl(String serviceAddress) {
        try {

            if(zk.exists(PATH, false) == null){
                zk.create(PATH, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }

            zk.create(PATH + "/address-" + serviceAddress, serviceAddress.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        if(watchedEvent.getState() == Event.KeeperState.SyncConnected) {
            System.out.println("process-----------------------");
        }

    }

    public static void main(String[] args) {
        Register reg = new Register("127.0.0.1:2181");

        reg.RegisterImpl("10.1.1.1:5000");
    }
}

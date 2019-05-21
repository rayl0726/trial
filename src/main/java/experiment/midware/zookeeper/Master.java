package experiment.midware.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.Random;

import static org.apache.zookeeper.ZooDefs.Ids.OPEN_ACL_UNSAFE;

/**
 * zookeeper 测试
 *
 * @author : lei.liu
 */
public class Master implements Watcher{

    private ZooKeeper zooKeeper;
    private String hostPort;

    private Master(String hostPort) {
        this.hostPort = hostPort;
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println(watchedEvent);
    }

    private void startZK() throws IOException {
        zooKeeper = new ZooKeeper(hostPort, 10000, this);
    }

    private void stopZK() throws Exception {
        zooKeeper.close();
    }

    private Random random = new Random();

    private String serverId = Integer.toHexString(random.nextInt());
    private static boolean isLeader = false;

    private boolean checkMaster() {
        while(true) {
            try {
                Stat stat = new Stat();
                byte[] data = new byte[0];
                data = zooKeeper.getData("/master", false, stat);
                isLeader = new String(data).equals(serverId);
                return true;
            } catch (KeeperException e) {
                e.printStackTrace();
                return false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private void runForMaster() throws KeeperException, InterruptedException {
        while (true) {
            try {
                zooKeeper.create("/master", serverId.getBytes(), OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
                isLeader = true;
                System.out.println(serverId);
                break;

            } catch(KeeperException.NodeExistsException e){
                isLeader = false;
                System.out.println(serverId);
            }

            if (checkMaster()) {
                break;
            }

        }
    }

    //异步创建节点方法
    private AsyncCallback.StringCallback masterCreateCallback = new AsyncCallback.StringCallback() {

        @Override
        public void processResult(int i, String s, Object o, String s1) {
            switch(KeeperException.Code.get(i)) {
                case CONNECTIONLOSS:
                    checkMaster2();
                    return;
                case OK:
                    isLeader = true;
                    break;
                default:
                    isLeader = false;
            }

            System.out.println("I'm" + (isLeader ? "" : "not") + "the leader");
        }
    };

    private void runForMaster2() {
        zooKeeper.create("/master2", serverId.getBytes(), OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL, masterCreateCallback, null);
    }

    //异步检查节点信息
    private AsyncCallback.DataCallback dataCallback = new AsyncCallback.DataCallback() {
        @Override
        public void processResult(int i, String s, Object o, byte[] bytes, Stat stat) {
            switch(KeeperException.Code.get(i)) {
                case CONNECTIONLOSS:
                    checkMaster2();
                    return;
                case NONODE:
                    runForMaster2();
                    return;
            }
        }
    };

    private void checkMaster2() {
        zooKeeper.getData("/master2", false, dataCallback, null);
    }


//    //设置元数据
//    public void bootstrap() {
//
//    }
//
//    void createParent(String path, byte[] data) {
//        zooKeeper.create(path, data, OPEN_ACL_UNSAFE, CreateMode.PERSISTENT, ,data);
//    }
//
//    AsyncCallback.StringCallback createParentCallback = new AsyncCallback.StringCallback() {
//        @Override
//        public void processResult(int i, String s, Object o, String s1) {
//            switch(KeeperException.Code.get(i)):
//
//        }
//    }



    public static void main(String[] args) throws Exception {
        Master master = new Master(args[0]);

        master.startZK();
        master.runForMaster2();

        if(isLeader) {
            System.out.println("I'm leader");
            System.out.println();
            Thread.sleep(20000);

        } else {
            System.out.println("leader is other one");
            Thread.sleep(20000);
        }
        master.stopZK();
    }
}

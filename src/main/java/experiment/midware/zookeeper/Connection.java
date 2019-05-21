package experiment.midware.zookeeper;

import org.apache.zookeeper.*;

import java.io.IOException;

/**
 * @author : liulei
 **/
public class Connection implements Watcher {

    private ZooKeeper zooKeeper;
    private String hostPort;

    public Connection(String hostPort) {
        this.hostPort = hostPort;
    }

    private void startZK() throws IOException {
        zooKeeper = new ZooKeeper(hostPort, 10000, null);
    }

    public void register(String name, String address) {
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("-------------------------" + watchedEvent);
    }


    public static void main(String[] args) throws IOException, InterruptedException {
        Connection con = new Connection("127.0.0.1:2181");
        con.startZK();
    }
}

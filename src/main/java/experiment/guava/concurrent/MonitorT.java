package experiment.guava.concurrent;

import com.google.common.util.concurrent.Monitor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : liulei
 **/
public class MonitorT {
    private List<String> list = new ArrayList<>();
    private static final int MAX_SIZE = 10;
    private Monitor monitor = new Monitor();
    private Monitor.Guard listBelowCapacity = new Monitor.Guard(monitor) {
        @Override
        public boolean isSatisfied() {
            return list.size() < MAX_SIZE;
        }
    };
    public void addToList(String item) throws InterruptedException {
        monitor.enterWhen(listBelowCapacity);
        try{
            list.add(item);
        }finally {
            monitor.leave();
        }
    }

}

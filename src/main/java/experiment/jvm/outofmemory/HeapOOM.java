package experiment.jvm.outofmemory;

import java.util.ArrayList;
import java.util.List;

/**
 * VM Args: -Xms20m -Xmx20m -XX:HeapDumpOnOutOfMemoryError
 * @author : liulei
 **/
public class HeapOOM {
    static class OOMObject {
    }

    public static void main(String[] args) throws InterruptedException {
        List<OOMObject> list = new ArrayList<>();
        while(true) {
//            Thread.sleep(1);
            list.add(new OOMObject());
        }
    }
}

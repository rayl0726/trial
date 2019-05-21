package experiment.midware.lettuce;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.api.sync.RedisCommands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author : liulei
 **/
public class Batch {

    private static void syn() {
        RedisClient client = RedisClient.create(RedisURI.Builder.redis("127.0.0.1").withPort(6379).withDatabase(1).build());
        client.setDefaultTimeout(20, TimeUnit.SECONDS);
        StatefulRedisConnection<String, String> connect = client.connect();
        RedisCommands<String, String> sync = connect.sync();
    }

    private static void asyn() {
        RedisClient client = RedisClient.create(RedisURI.Builder.redis("127.0.0.1").withPort(6379).withDatabase(1).build());
        client.setDefaultTimeout(20, TimeUnit.SECONDS);
        StatefulRedisConnection<String, String> connect = client.connect();
        RedisAsyncCommands<String, String> async = connect.async();
        async.setAutoFlushCommands(false);

        Map<String,String> resp = new HashMap<String, String>();
        List<RedisFuture> futures = new ArrayList<RedisFuture>();
        for(int i = 0; i < 100; i++) {
            RedisFuture<String> future = async.get("teacher_" + i + "_status");
//            resp.put("teacher_" + i + "_status", future.get());
        }

        System.out.println(resp);
        async.flushCommands();

        connect.close();
    }
    public static void main(String[] args) throws Exception{
        asyn();
    }
}

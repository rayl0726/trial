package experiment.midware.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : liulei
 **/
public class RedisPipeline {

    public static void insert() {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        System.out.println(jedis.ping());

        jedis.select(1);
        Pipeline pipelined = jedis.pipelined();
        HashMap<String, Response<String>> map = new HashMap<String, Response<String>>();
        long start = System.currentTimeMillis();
        for ( int i = 0; i < 1000; i++) {
            String key = "teacher_" + i + "_status";
            pipelined.set(key, i + "_STA");

        }
        long end = System.currentTimeMillis();
        jedis.close();
        System.out.println("during time: " + (end-start));
    }


    public static void batchGet() {
        Map<String, String> ret = new HashMap<String, String>();
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        System.out.println(jedis.ping());

        jedis.select(1);
        Pipeline pipelined = jedis.pipelined();
        HashMap<String, Response<String>> map = new HashMap<String, Response<String>>();
        long start = System.currentTimeMillis();
        for ( int i = 0; i< 100; i++) {
            String key = "teacher_" + i + "_status";
            map.put(key, pipelined.get(key));

        }

        pipelined.sync();
        jedis.close();

        for (Map.Entry<String, Response<String>> entry :map.entrySet()) {
            Response<String> sResponse = entry.getValue();
            String key = new String(entry.getKey());
            String value = sResponse.get();
            ret.put(key, value);
        }

        long end = System.currentTimeMillis();
        System.out.println("during time: " + (end-start));
        jedis.close();
        System.out.println(ret);
    }


    public static void main(String[] args) {
//        insert();
        batchGet();
    }
}

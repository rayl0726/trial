package experiment.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.util.HashMap;

/**
 * @author : liulei
 **/
public class RedisBatch {


    public static void insert() {
        Jedis jedis = new Jedis("127.0.0.1", 9000);
        System.out.println(jedis.ping());

        jedis.select(1);
        Pipeline pipelined = jedis.pipelined();
        long start = System.currentTimeMillis();
        for ( int i = 0; i < 1000; i++) {
            String key = "teacher_" + i + "_status";
            jedis.set(key, i + "_NO_STA");
        }
        long end = System.currentTimeMillis();
        System.out.println("during time: " + (end-start));
    }

    public static void batchGet() {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        System.out.println(jedis.ping());

        jedis.select(1);
        HashMap<String, String> map = new HashMap<String, String>();
        long start = System.currentTimeMillis();
        for ( int i = 0; i< 10000; i++) {
            String key = "teacher_" + i + "_status";
            map.put(key, jedis.get(key));
        }
        long end = System.currentTimeMillis();
        System.out.println(map);
        System.out.println("during time: " + (end-start));
    }
    public static void main(String[] args) {
        insert();
        batchGet();
    }
}

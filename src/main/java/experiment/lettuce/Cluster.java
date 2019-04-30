package experiment.lettuce;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.RedisURI;
import io.lettuce.core.TransactionResult;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.lettuce.core.cluster.api.async.RedisAdvancedClusterAsyncCommands;
import io.lettuce.core.cluster.api.sync.RedisAdvancedClusterCommands;
import io.lettuce.core.codec.StringCodec;
import io.lettuce.core.support.*;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import java.util.*;
import java.util.concurrent.*;

/**
 * @author : liulei
 **/
public class Cluster {

    private static GenericObjectPool<StatefulRedisClusterConnection<String, String>> pool;

    //存放老师心跳的状态
    private static Set<String> heartBeatSet = new HashSet<>();

    static {
        RedisURI node9000 = RedisURI.builder().withHost("127.0.0.1").withPort(9000).build();
        RedisURI node9001 = RedisURI.builder().withHost("127.0.0.1").withPort(9001).build();
        RedisURI node9002 = RedisURI.builder().withHost("127.0.0.1").withPort(9002).build();
        RedisURI node9003 = RedisURI.builder().withHost("127.0.0.1").withPort(9003).build();
        RedisURI node9004 = RedisURI.builder().withHost("127.0.0.1").withPort(9004).build();
        RedisURI node9005 = RedisURI.builder().withHost("127.0.0.1").withPort(9005).build();

        RedisClusterClient redisClusterClient = RedisClusterClient.create(Arrays.asList(node9000, node9001, node9002, node9003, node9004,node9005));

        pool = ConnectionPoolSupport
                .createGenericObjectPool(() -> redisClusterClient.connect(), new GenericObjectPoolConfig());
    }


    public static GenericObjectPool synPool() {
        RedisURI node9000 = RedisURI.builder().withHost("127.0.0.1").withPort(9000).build();
        RedisURI node9001 = RedisURI.builder().withHost("127.0.0.1").withPort(9001).build();
        RedisURI node9002 = RedisURI.builder().withHost("127.0.0.1").withPort(9002).build();
        RedisURI node9003 = RedisURI.builder().withHost("127.0.0.1").withPort(9003).build();
        RedisURI node9004 = RedisURI.builder().withHost("127.0.0.1").withPort(9004).build();
        RedisURI node9005 = RedisURI.builder().withHost("127.0.0.1").withPort(9005).build();

        RedisClusterClient redisClusterClient = RedisClusterClient.create(Arrays.asList(node9000, node9001, node9002, node9003, node9004,node9005));

        GenericObjectPool<StatefulRedisClusterConnection<String, String>> pool = ConnectionPoolSupport
                .createGenericObjectPool(() -> redisClusterClient.connect(), new GenericObjectPoolConfig());
        return pool;
    }

    public static RedisClusterClient asyn() throws Exception{
        RedisURI node9000 = RedisURI.builder().withHost("127.0.0.1").withPort(9000).build();
        RedisURI node9001 = RedisURI.builder().withHost("127.0.0.1").withPort(9001).build();
        RedisURI node9002 = RedisURI.builder().withHost("127.0.0.1").withPort(9002).build();
        RedisURI node9003 = RedisURI.builder().withHost("127.0.0.1").withPort(9003).build();
        RedisURI node9004 = RedisURI.builder().withHost("127.0.0.1").withPort(9004).build();
        RedisURI node9005 = RedisURI.builder().withHost("127.0.0.1").withPort(9005).build();

        RedisClusterClient redisClusterClient = RedisClusterClient.create(Arrays.asList(node9000, node9001, node9002, node9003, node9004,node9005));

        StatefulRedisClusterConnection<String, String> connect = redisClusterClient.connect();
        RedisAdvancedClusterAsyncCommands<String, String> commands = connect.async();
        commands.setAutoFlushCommands(false);

//        RedisFuture<String> getTest = commands.set("teacher_200_status", "200");
//        commands.flushCommands();
//        System.out.println(getTest.get());
        System.out.println("init success");
        return redisClusterClient;
    }

    public static BoundedAsyncPool<StatefulRedisClusterConnection<String, String>> lettucePool(RedisClusterClient clusterClient) {
        final BoundedPoolConfig.Builder builder = BoundedPoolConfig.builder();
        builder.minIdle(9);
        final BoundedPoolConfig boundedPoolConfig = builder.build();
        final BoundedAsyncPool<StatefulRedisClusterConnection<String, String>> lettucePool = AsyncConnectionPoolSupport.createBoundedObjectPool(
                () -> clusterClient.connectAsync(StringCodec.UTF8)
                , boundedPoolConfig
        );

        System.out.println("连接池初始化成功");
        return lettucePool;
    }

    public static CompletableFuture<StatefulRedisClusterConnection<String, String>> clusterAsync(BoundedAsyncPool<StatefulRedisClusterConnection<String, String>> lettucePool) {

        final CompletableFuture<StatefulRedisClusterConnection<String, String>> acquire = lettucePool.acquire();
        return acquire;
    }

    public static void asynPool() {
        RedisURI node9000 = RedisURI.create("127.0.0.1", 9000);
        RedisURI node9001 = RedisURI.create("127.0.0.1", 9001);
        RedisURI node9002 = RedisURI.create("127.0.0.1", 9002);
        RedisURI node9003 = RedisURI.create("127.0.0.1", 9003);
        RedisURI node9004 = RedisURI.create("127.0.0.1", 9004);
        RedisURI node9005 = RedisURI.create("127.0.0.1", 9005);
        final RedisClusterClient redisClusterClient = RedisClusterClient.create(Arrays.asList(node9000, node9001, node9002, node9003, node9004,node9005));

        AsyncPool<StatefulRedisClusterConnection<String, String>> pool =
                AsyncConnectionPoolSupport.createBoundedObjectPool(() -> redisClusterClient.connectAsync(StringCodec.UTF8), BoundedPoolConfig.create());

        CompletableFuture<String> stringCompletableFuture = pool.acquire().thenCompose(connection -> {
            RedisAdvancedClusterAsyncCommands<String, String> async = connection.async();

            RedisFuture<String> stringRedisFuture1 = async.get("teacher_" + 1 + "_status");
            RedisFuture<String> stringRedisFuture2 = async.get("teacher_" + 2 + "_status");
            return async.get("teacher_" + 3 + "_status").whenComplete((s, throwable) -> {pool.release(connection);
                System.out.println(stringRedisFuture1);
                System.out.println(stringRedisFuture2);} );
        });
        System.out.println(stringCompletableFuture.isDone());
        pool.closeAsync();


        redisClusterClient.shutdownAsync();

    }

    //登录事件：redis写入key
    //参数：老师id， 老师登录时间， 老师登录连接服务器节点，
    public static void online(String teacherId, String node, String loginTime) {
        long expire = 200;
        String key = "TeacherOnline:" + teacherId;
//        GenericObjectPool pool = synPool();
        StatefulRedisClusterConnection<String, String> connection = null;
        try {
            connection = (StatefulRedisClusterConnection<String, String>) pool.borrowObject();
            //登陆前先判断有没有key存在，若存在，则证明之前已经登录
            RedisAdvancedClusterCommands<String, String> commands = connection.sync();
            Long exists = commands.exists(key);
            if(exists > 0) {
                //当前key存在，老师已经登录,此时可能会存在老师在新的节点登录，或者老师在同一个节点登录
                System.out.println("key exist");
                commands.sadd(key, node);
                commands.expire(key, expire);
            } else {
                System.out.println("key not exist");
                //key不存在，老师是首次登陆
                commands.sadd(key, loginTime, node);
                //心跳时间为20s，设置两倍的多的心跳时间来作为超时时间，暂定50s
                commands.expire(key, expire);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(connection != null) {
                pool.returnObject(connection);
            }
        }
    }

    public static void offline(String teacherId, String node) {
        long expire = 200;
        String key = "TeacherOnline:" + teacherId;
//        GenericObjectPool pool = synPool();
        StatefulRedisClusterConnection<String, String> connection = null;
        try {
            connection = pool.borrowObject();
            RedisAdvancedClusterCommands<String, String> commands = connection.sync();
            //正常下线前判断key是否存在，如果不存在，则可能是在上次的更新失败，不进行操作
            Long exists = commands.exists(key);
            if(exists > 0) {
                //当前key存在，老师已经登录,此时可能会存在老师在新的节点登录，或者老师在同一个节点登录
                System.out.println("key exist");
                if(commands.zcard(key) > 2) {
                    //证明用户登陆了多个节点
                    if(commands.sismember(key, node)){
                        //先判断要删除的节点信息是否存在，不存在的话不操作
                        commands.zrem(key, node);
                        commands.expire(key, exists);
                    }
                } else{
                    //老师登录了一个节点
                    commands.del(key);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(connection != null) {
                pool.returnObject(connection);
            }
        }
    }
    //把心跳的老师的节点存储在一个节点中,当一个老师有多个节点的时候
    public static void heartBeat(String teacherId) {
        String key = "TeacherOnline:" + teacherId;
        heartBeatSet.add(key);
    }

    //采用定时任务把set中的心跳数据消费
    public static void flushHeartBeat() {
        StatefulRedisClusterConnection<String, String> connection = null;
        try {
            connection = pool.borrowObject();
            RedisAdvancedClusterAsyncCommands<String, String> commands = connection.async();
            //异步连接，命令采用pipeline刷新
            commands.setAutoFlushCommands(false);
            if(heartBeatSet != null) {
                for(String temp : heartBeatSet) {
                    commands.expire(temp, 200);
                }
            }
            commands.flushCommands();
        } catch (Exception e ) {
            e.printStackTrace();
        } finally {
            if(connection != null) {
                pool.returnObject(connection);
            }
        }
    }


    public static void main(String[] args) throws Exception{
        String test = "loginTime:123123";
        int i = test.indexOf(":");
        String substring = test.substring(i+1);
        System.out.println(substring);
    }
}

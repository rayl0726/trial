package experiment.jedis;

import redis.clients.jedis.*;

import java.util.*;

/**
 * @author : liulei
 **/
public class RedisCluster {
    private static final String host = "127.0.0.1";
    public static void main(String[] args) throws Exception{

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(10);
        jedisPoolConfig.setMaxIdle(5);

        Set<HostAndPort> nodes = new LinkedHashSet<HostAndPort>();
        nodes.add(new HostAndPort(host, 9000));
        nodes.add(new HostAndPort(host, 9001));
        nodes.add(new HostAndPort(host, 9002));
        nodes.add(new HostAndPort(host, 9003));
        nodes.add(new HostAndPort(host, 9004));
        nodes.add(new HostAndPort(host, 9005));

        JedisCluster cluster = new JedisCluster(nodes, jedisPoolConfig);

//        for ( int i = 0; i < 1000; i++) {
//            String key = "teacher_" + i + "_status";
//            cluster.set(key, i + "_NO_STA");
//        }

        cluster.get("teacher_726_status");

        cluster.get("teacher_110_status");
        cluster.close();
//        HashMap<String, String> map = new HashMap<String, String>();
        //常规模式批量获取
//        for(int i = 0; i < 100; i++) {
//            String key = "teacher_" + i + "_status";
//            System.out.println(cluster.get(key));
////            map.put(key, cluster.get(key));
//        }
//        System.out.println(map);

//        int teacher_110_status = JedisClusterCRC16.getSlot("teacher_110_status");
//        System.out.println(teacher_110_status);
//
//        Map<String, JedisPool> clusterNodes = cluster.getClusterNodes();
//        String next = clusterNodes.keySet().iterator().next();
//        TreeMap<Long, String> slotHostMap = getSlotHostMap(next);
//
//        Map.Entry<Long, String> longStringEntry = slotHostMap.lowerEntry(Long.valueOf(teacher_110_status));
//
//        Jedis resource = clusterNodes.get(longStringEntry.getValue()).getResource();
//
//        String teacher_110_status1 = resource.get("teacher_110_status");
//        System.out.println(teacher_110_status1);
    }

    private static TreeMap<Long, String> getSlotHostMap(String anyHostAndPortStr) {
        TreeMap<Long, String> tree = new TreeMap<Long, String>();
        String parts[] = anyHostAndPortStr.split(":");
        HostAndPort anyHostAndPort = new HostAndPort(parts[0], Integer.parseInt(parts[1]));
        try{
            Jedis jedis = new Jedis(anyHostAndPort.getHost(), anyHostAndPort.getPort());
            List<Object> list = jedis.clusterSlots();
            for (Object object : list) {
                List<Object> list1 = (List<Object>) object;
                List<Object> master = (List<Object>) list1.get(2);
                String hostAndPort = new String((byte[]) master.get(0)) + ":" + master.get(1);
                tree.put((Long) list1.get(0), hostAndPort);
                tree.put((Long) list1.get(1), hostAndPort);
            }
            jedis.close();
        }catch(Exception e){

        }
        return tree;
    }
}

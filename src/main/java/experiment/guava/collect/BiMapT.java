package experiment.guava.collect;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

/**
 * @author : liulei
 **/
public class BiMapT {
    public static void main(String[] args) {
        //bimap 的值也是唯一的，不允许重复，重复会抛出异常：IllegalArgumentException
        BiMap<String, String> biMap = HashBiMap.create();
        biMap.put("1", "tom");
        //focePut 当value已存在的情况下会强制吧之前的key-value变成当前的key
        biMap.forcePut("2", "jack");
        biMap.put("3", "jerry");
        System.out.println(biMap);

        //翻转k-v
        BiMap<String, String> inverse = biMap.inverse();
        System.out.println(inverse);

    }
}

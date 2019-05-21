package experiment.guava.collect;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultimap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * @author : liulei
 **/
public class MultimapsT {
    public static void main(String[] args) {
        ArrayListMultimap<String, String> multimap = ArrayListMultimap.create();
        multimap.put("Bar", "1");
        multimap.put("Bar", "2");
        multimap.put("Bar", "3");
        multimap.put("Foo", "1");
        multimap.put("Foo", "2");
        multimap.put("Foo", "3");
        //size和values获取的都是整个map的值，不是map里面的list
        System.out.println(multimap.size());
        System.out.println(multimap.values());
        //转成map
        Map<String, Collection<String>> stringCollectionMap = multimap.asMap();
        //bu支持再添加元素
        //stringCollectionMap.put("1", new ArrayList<>());
        System.out.println(stringCollectionMap.size());
        System.out.println(stringCollectionMap.values());


        HashMultimap<String, String> hashMultimap = HashMultimap.create();
        hashMultimap.put("Bar", "1");
        hashMultimap.put("Bar", "2");
        hashMultimap.put("Bar", "3");
        hashMultimap.put("Bar", "3");
        hashMultimap.put("Bar", "3");
        //不支持重复的元素
        System.out.println(hashMultimap.size());
    }
}

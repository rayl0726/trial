package experiment.guava.collect;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * @author : liulei
 **/
public class SetsT {
    public static void main(String[] args) {
        Set<String> s1 = Sets.newHashSet("3","4","5","6");
        Set<String> s2 = Sets.newHashSet("1","2","3");
        //difference只能返回第一个set中的和第二个set不同的元素
        Sets.SetView<String> difference = Sets.difference(s1, s2);
        //symmetricDifference返回两个set中共同的不相同元素
        Sets.SetView<String> strings = Sets.symmetricDifference(s1, s2);
        //intersection返回两个set的共同元素
        Sets.SetView<String> intersection = Sets.intersection(s1, s2);
        //union合并两个set,其实是把第二个set的元素追加到第一个set后面
        Sets.SetView<String> union = Sets.union(s1, s2);
    }
}

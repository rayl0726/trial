package experiment.guava.collect;

import com.google.common.collect.ImmutableListMultimap;

/**
 * @author : liulei
 **/
public class ImmutableT {
    public static void main(String[] args) {
        ImmutableListMultimap<Object, Object> build = new ImmutableListMultimap.Builder()
                .put(1, "foo")
                .putAll(2, "Foo", "Bar", "baz")
                .putAll(4, "Huey", "Duey", "Luey")
                .put(3, "Single").build();

        System.out.println(build);
    }
}

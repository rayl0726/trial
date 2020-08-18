package experiment.guava.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author liulei
 */
public class CacheTest {
    private static Cache<Object, Object> cache = CacheBuilder.newBuilder().expireAfterWrite(5, TimeUnit.SECONDS).build();

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        cache.put(123, 777);
        Thread.sleep(6 * 1000);
        Object res = cache.get(123, new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 1;
            }
        });
        System.out.println(res);
        Thread.sleep(3 * 1000);

        Object res2 = cache.get(123, new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 0;
            }
        });

        System.out.println(res2);

    }
}

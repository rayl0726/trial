package experiment.guava.ratelimter;

import com.google.common.util.concurrent.RateLimiter;

import java.time.LocalDateTime;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author liulei
 */
public class RateLimiterTest {

    public static void main(String[] args) {
        RateLimiter rateLimiter = RateLimiter.create(5.0);
        Executor exe = Executors.newFixedThreadPool(10);
        while (true) {
            exe.execute(new Runnable() {
                public void run() {
                    rateLimiter.acquire();
                    System.out.println(LocalDateTime.now());
                }
            });
        }
    }
}

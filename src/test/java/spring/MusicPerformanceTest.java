package spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spring.aspect.PerformanceConfig;
import spring.aspect.Performance;

/**
 * @author : liulei
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PerformanceConfig.class})
public class MusicPerformanceTest {
    @Autowired
    private Performance performance;

    @Test
    public void performance() {
        performance.perform();
    }
}

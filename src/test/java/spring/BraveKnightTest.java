package spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spring.config.KnightConfig;

import static org.junit.Assert.*;

/**
 * @author : liulei
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {KnightConfig.class})
//@ContextConfiguration(locations = {"classpath*:/spring.xml"})
@ActiveProfiles("dev")
public class BraveKnightTest {

    @Autowired
    private BraveKnight braveKnight;

    @Test
    public void knightNotNull() {
        assertNotNull(braveKnight);
        braveKnight.execute();
    }
}
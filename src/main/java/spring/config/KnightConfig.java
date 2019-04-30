package spring.config;

import org.springframework.context.annotation.*;
import spring.*;

/**
 * @author : liulei
 **/
@Configuration
@ComponentScan(basePackages = "spring")
public class KnightConfig {

//    @Bean
//    public Knight buildBraveKnight() {
//        return new BraveKnight();
//    }
//
//    @Bean
//    public Knight buildHardKnight() {
//        return new HardKnight();
//    }

//    @Bean
//    @Profile("dev")
//    public Quest dragonQuest() {
//        return new DragonQuest("tom");
//    }
//
//    @Bean
//    @Profile("test")
//    public Quest pigQuest() {
//        return new PigQuest("tom");
//    }

    @Bean
    public String name() {
        return "tom";
    }

    @Bean
    public String name1() {
        return "tommy";
    }
}

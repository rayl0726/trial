package spring.aspect;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author : liulei
 **/
@EnableAspectJAutoProxy
@Configuration
@ComponentScan(basePackages = {"spring.aspect"})
public class PerformanceConfig {

    @Bean
    public Audience audience() {
        return new Audience();
    }

    @Bean
    public Performance performance() {
        return new MusicPerformance();
    }

}

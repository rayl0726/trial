package spring.aspect;

import org.springframework.stereotype.Component;

/**
 * @author : liulei
 **/
public class MusicPerformance implements Performance {
    @Override
    public void perform() {
        System.out.println("music playing ##$$@@!@#!");
    }
}

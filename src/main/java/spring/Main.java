package spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.io.InputStream;
import java.net.URL;

/**
 * @author : liulei
 **/
public class Main {
    public static void main(String[] args) {
//        URL resource = Main.class.getClassLoader().getResource("/spring.xml");
        String path = "/home/liulei/own-projects/java-projects/practice/src/main/resources/spring.xml";
        ApplicationContext context = new FileSystemXmlApplicationContext(path);
        Knight bean = context.getBean(Knight.class);
        bean.execute();
    }
}

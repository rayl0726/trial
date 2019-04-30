package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


/**
 * @author : liulei
 **/
@Component
@Profile("test")
public class DragonQuest implements Quest{
    @Autowired
    private String name;

    public DragonQuest(String name) {
        this.name = name;
    }

    @Override
    public void mession() {
        System.out.println(name + " slay dragon");
    }


}







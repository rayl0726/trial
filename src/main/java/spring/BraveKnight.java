package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @author : liulei
 **/
@Component
public class BraveKnight implements Knight {

    @Autowired
    private Quest quest;

//    BraveKnight(Quest quest){
//        this.quest = quest;
//    }

    @Override
    public void execute() {
        quest.mession();
    }
}

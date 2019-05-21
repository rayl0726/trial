package experiment.guava.utils;

import com.google.common.base.Splitter;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author : liulei
 **/
public class SplitterT {

    public static void print(Iterable<String> strings) {
        for (String temp : strings) {
            System.out.println(temp);
        }
    }
    public static void main(String[] args) {
        String str = "  Monday,           Tuesday ,    ,Thursday,Friday,,1";
        String[] split = str.split(",");
        //trim 去掉头和尾的空格
        Iterable<String> split1 = Splitter.on(",").trimResults().split(str);
        print(split1);

//        Map<String,String> testMap = Maps.newLinkedHashMap();
//        testMap.put("Washington D.C","Redskins");
//        testMap.put("New York City","Giants");
//        testMap.put("Philadelphia","Eagles");
//        testMap.put("Dallas","Cowboys");
        String startString = "Washington D.C=Redskins#New YorkCity=Giants#Philadelphia=Eagles#Dallas=Cowboys";
        Splitter.MapSplitter mapSplitter = Splitter.on("#").withKeyValueSeparator("=");
        Map<String, String> split2 = mapSplitter.split(startString);


    }
}

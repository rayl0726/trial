package experiment.guava.utils;

import com.google.common.base.Joiner;
import com.google.common.collect.Maps;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author : liulei
 **/
public class JoinerT {

    //原始的整合spring的方案
    public static String buildString(List<String> stringList, String delimiter) {
        StringBuilder builder = new StringBuilder();
        for (String s : stringList) {
            if (s != null) {
                builder.append(s).append(delimiter);
            }
        }

        System.out.println(builder.length());
        builder.setLength(builder.length() - delimiter.length());
        return builder.toString();
    }

    public static void print(List<String> list) {
        for (String temp : list) {
            System.out.println(temp);
        }
    }
    public static void main(String[] args) throws IOException {
        List<String> list = new ArrayList<String>(10);
        for (int i = 0; i < 10; i++) {
            if(i == 3) {
                list.add(null);
            } else {
                list.add("string:" + i);
            }
        }
        System.out.println(Joiner.on("|").useForNull("no value").join(list));

        //joiner不仅仅可以返回string类型，还可以返回Object类型
        StringBuilder stringBuilder = new StringBuilder();
        Joiner joiner = Joiner.on("|").useForNull("no value");
        StringBuilder stringBuilder1 = joiner.appendTo(stringBuilder, "string1", "string2", null, "string3");

//        FileWriter fileWriter = new FileWriter(new File("/home/liulei/test.txt"));
//        FileWriter fileWriter1 = joiner.appendTo(fileWriter, list);
//        fileWriter1.flush();

        //map中的k-v可以整合到一个string中
        Joiner.MapJoiner mapJoiner = Joiner.on("#").withKeyValueSeparator("=");
        String expectedString = "Washington D.C=Redskins#New YorkCity=Giants#Philadelphia=Eagles#Dallas=Cowboys";
        Map<String,String> testMap = Maps.newLinkedHashMap();
        testMap.put("Washington D.C","Redskins");
        testMap.put("New York City","Giants");
        testMap.put("Philadelphia","Eagles");
        testMap.put("Dallas","Cowboys");
        String returnedString = Joiner.on("#").
                withKeyValueSeparator("=").join(testMap);
        System.out.println(returnedString);



    }
}

package experiment.offer;

import org.omg.PortableServer.THREAD_POLICY_ID;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author : liulei
 **/
public class No3 {
//    public static int getRepeat(int[] source)  {
//        int[] temp = new int[source.length];
//        for(int temp )
//
//
public static void main(String[] args) throws Exception{
    Thread.sleep(3000);
    String name = ManagementFactory.getRuntimeMXBean().getName();
    System.out.println(name);

    String[] split = name.split("@");
    System.out.println(split[0]);
    Thread.sleep(2000);


    String fileName="/home/liulei/No3.pid";
    FileWriter writer=new FileWriter(fileName,false);
    writer.write(split[0]);
    writer.close();

    }
}

package work.test;

import java.util.*;
import java.util.Iterator;

/**
 * @author : liulei
 **/
public class CommonT {

//    public static void print(Car car) {
//        String printStr = "abc";
//
//        car.start(printStr);
//
//    }
//
//    public static void test() {
//
//    }
//
//
//    interface Car {
//        void start(String str);
//    }
//
//    interface Animal<T>{
//        T run();
//    }
//    static Car car = new Car() {
//        @Override
//        public void start(String str) {
//            System.out.println(str);
//        }
//    };

    private static int[] ids = {551,551,531,550,532,550,180490,553,180490,554,530,180490,179163,550,550,556,554,561,561,561,554,557,560,557,179655,556,563,556,557,558,563,565,558,564,565,445,565,564,558,556,565,558,565,558,445,568,564,566,565,568,563,564,569,570,571,564,568,565,530,570,565,530,558,570,569,574,564,575,530,575,556,387,575,570,576,556,564,556,567,556,387,574,574,556,580,445,576,556,580,582,567,580,556,580,556,575,580,575,582,556,575,583,585,584,582,583,586,580,575,583,586,576,587,584,580,586,584,586,
            550,551,548,530,549,549,550,550,532,553,550,550,553,530,546,554,553,556,553,556,553,556,554,557,554,557,560,561,560,565,563,565,558,554,563,560,556,563,560,179655,556,564,566,566,563,445,566,563,571,556,564,569,556,566,564,571,567,530,570,556,387,570,387,575,576,575,579,574,576,575,580,579,567,576,575,576,580,530,576,582,580,581,582,580,567,581,585,580,586,582,575,586,580,587,556,587,580,556,586,587,586,576,575,586,556};


    public static void main(String[] args) throws Exception {
        HashSet<Integer> idNoRepet = new HashSet<>();
        for (Integer temp : ids) {
            idNoRepet.add(temp);
        }
        System.out.println("影响总用户数：" + idNoRepet.size());
        System.out.println("------------------------------------------");
        System.out.println("三位数的id是181 开头");

        getRepetNum();
    }

    private static void getRepetNum() {
        Map<Integer, Integer> map = new HashMap<>();
        for(int item: ids){
            if(map.containsKey(item)){
                map.put(item, map.get(item).intValue() + 1);
            }else{
                map.put(item, new Integer(1));
            }
        }

        Iterator<Integer> keys = map.keySet().iterator();

        while(keys.hasNext()){
            Integer key = keys.next();
            System.out.println(key + ":" + map.get(key).intValue() + ", ");
        }
    }
}

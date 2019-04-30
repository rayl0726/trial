package experiment;

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


    public static void main(String[] args) throws Exception {
//        Season spring = Season.SPRING;
//        System.out.println(spring.name() + " : " + spring.getCode());
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10 ; i++) {
            list.add(i);
        }

        Iterator<Integer> iterator = list.iterator();
        while(iterator.hasNext()){
            Integer next = iterator.next();
            if(next == 3) {
                iterator.remove();
                System.out.println(next);
            }
        }

//        for(Integer temp : list) {
//            System.out.println(temp);
//        }
    }
}

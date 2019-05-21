package experiment.base;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * @author : liulei
 **/
public class PriorityQueueTest {


    public static void main(String[] args) {
//        PriorityQueue<People> queue = new PriorityQueue<>(11, new Comparator<People>() {
//            @Override
//            public int compare(People o1, People o2) {
//                return o2.age -o1.age;
//            }
//        });

        PriorityQueue<People> queue = new PriorityQueue<>(11);

        for (int i = 1; i <=10; i++) {
            queue.add(new People("张" + i, (new Random().nextInt(100))));
        }
        while (!queue.isEmpty()) {
            System.out.println(queue.poll().toString());
        }
    }

    static class People implements Comparable<People>{
        String name;
        int age;

        private People(String name, int age) {
            this.age = age;
            this.name = name;
        }

        @Override
        public String toString() {
            return "name: " + name + " age: " + age;
        }


        @Override
        public int compareTo(People people) {
            return name.compareTo(people.name);
        }
    }
}

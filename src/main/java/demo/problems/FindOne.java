package demo.problems;

/**
 * @author liulei
 */
public class FindOne {
    /**
     * 统计从1到给定数的1 出现的次数， 11算两次
     * @param num
     * @return
     */
    public static int method(int num) {
        int count = 0;
        for (int i = 1; i <= num; i++) {
            count += count(i);
        }
        return count;
    }


    private static int count(int num) {
        int count = 0;
        while (num > 0) {
            count = count + (num % 10 == 1 ? 1 : 0);
            num = num / 10;
        }
        return count;
    }


    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println("start time:" + System.currentTimeMillis());
        System.out.println(method(1_0000_0000));
        long end = System.currentTimeMillis();
        System.out.println("end time:" + System.currentTimeMillis());
        System.out.println("cost:" + (end - start));

    }
}

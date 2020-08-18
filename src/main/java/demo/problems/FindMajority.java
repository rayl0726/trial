package demo.problems;

import java.util.Arrays;

/**
 * @author liulei
 */
public class FindMajority {

    /**
     * 分治的思想
     *
     * 寻找数组中超过一半的元素
     * @param array
     * @return
     */
    public static int method1(int[] array) {
        int length = array.length;
        int candidate =0;
        int times, i;
        for(i = times = 0; i < length; i++) {
            if(times == 0) {
                candidate = array[i];
                times = 1;
            } else if(candidate == array[i]){
                times++;
            } else {
                times--;
            }

        }
        return candidate;
    }


    /**
     * 数组中有3个元素超过 1/4 寻找此三个元素
     * @param array
     * @return
     */
    public static int[] method2(int[] array) {
        int[] candidate = {0, 0, 0};
        int[] times = {0, 0, 0};
        for (int value : array) {
            if (candidate[0] == value) {
                times[0]++;
            } else if (candidate[1] == value) {
                times[1]++;
            } else if (candidate[2] == value) {
                times[2]++;
            } else if (times[0] == 0 ) {
                candidate[0] = value;
                times[0] = 1;
            } else if (times[1] == 0 ) {
                candidate[1] = value;
                times[1] = 1;
            } else if (times[2] == 0) {
                candidate[2] = value;
                times[2] = 1;
            } else  {
                times[0]--;
                times[1]--;
                times[2]--;
            }
        }
        return candidate;
    }

    public static void main(String[] args) {
        int[] array1 = {1, 1, 1, 2, 2, 3};
        int[] array2 = {1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 5};
        System.out.println(method1(array1));
//        System.out.println(Arrays.toString(method2(array2)));
    }
}

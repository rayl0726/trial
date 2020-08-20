package demo.problems;

import java.util.Arrays;

/**
 * @author liulei
 */
public class Fibonacci {

    public static int method1(int n) {
        if(n < 0) {
            return -1;
        }
        if(n < 2) {
            return n;
        }  else {
            return method1(n-1) + method1( n-2);
        }
    }

    /**
     * 自底向上动态规划
     * @param n
     * @return
     */
    public static int method2(int n) {
        if(n < 2) {
            return n;
        }

        int[] temp = new int[n+1];
        temp[0] = 0;
        temp[1] = 1;
        int i = 2;

        while(i <= n) {
            temp[i] = temp[i -1] + temp[i -2];
            i++;
        }
        return temp[n];

    }

    /**
     * 自顶向上动态规划
     * @param n
     * @return
     */
    public static int method3(int n) {
        if(n < 0) {
            return -1;
        }

        int[] temp = new int[n + 1];
        Arrays.fill(temp, -1);
        return fib(n, temp);

    }

    private static int fib(int n, int[] temp) {
        if(temp[n] != -1)
            return temp[n];
        if(n < 2) {
            return n;
        } else {
            temp[n] = fib(n-1, temp) + fib(n-2, temp);
        }
        return temp[n];

    }

    public static void main(String[] args) {
        System.out.println(method2(20));
        System.out.println(method1(20));
        System.out.println(method3(20));
    }
}

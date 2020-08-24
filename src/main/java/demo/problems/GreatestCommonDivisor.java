package demo.problems;

/**
 * @author liulei
 */
public class GreatestCommonDivisor {


    /**
     * 递归的方式
     * @param a
     * @param b
     * @return
     */
    public static int method1(int a, int b) {
        if(b == 0) {
            return a;
        }
        return method1(b, a%b);
    }

    /**
     * 迭代方式
     * @param a
     * @param b
     * @return
     */
    public static int method2(int a, int b) {
        while (b != 0) {
            int temp = a;
            a = b;
            b = temp%b;
        }
        return a;
    }

    public static int method3(int a, int b) {
        if(a < b) {
            return method3(b, a);
        }
        if(b == 0) {
            return a;
        } else {
            return method3(a - b, b);
        }
    }





    public static void main(String[] args) {
        System.out.println(method1(30, 12));
        System.out.println(method2(30, 12));
        System.out.println(method3(30, 12));
    }
}

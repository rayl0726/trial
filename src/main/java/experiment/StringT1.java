package experiment;

/**
 * 测试 final 修饰的两个 String 相加在编译阶段会被优化成一个常量池的对象
 * 非 final 修饰的话编译阶段不会优化，所以在运行阶段会消耗内存
 * 采用 javac 编译看 class 文件
 *
 * @author : liulei
 **/
public class StringT1 {
    static final String TEST1 = "test1";
    static final String TEST2 = "test2";

    static String TEST3 = "test3";
    static String TEST4 = "test4";

    public static void main(String[] args) {

        System.out.println(TEST1 + TEST2);
        System.out.println(TEST3 + TEST4);
    }
}

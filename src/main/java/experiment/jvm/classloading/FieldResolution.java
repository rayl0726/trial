package experiment.jvm.classloading;

/**
 * @author : liulei
 **/
public class FieldResolution {
    interface Interface0 {
        int a = 0;
    }

    interface Interface1 extends Interface0 {
        int a = 1;
    }

    interface Interface2 {
        int a = 2;
    }

//    static class GrandPa implements Interface2 {
//        public static int a = 5;
//    }

    static class Parent implements Interface1 {
        public static int a = 3;
    }

    static class Sub extends Parent implements Interface2{
        public static int a = 4;
    }

    public static void main(String[] args) {
        System.out.println(Sub.a);
    }

}

package experiment.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @author : liulei
 **/
public class ChangeStaticFinalFieldSample2 {

     static void changeStaticFinal(Field field, Object newValue,Object obj) throws Exception {
        field.setAccessible(true);

        Field modifersField = Field.class.getDeclaredField("modifiers");
        modifersField.setAccessible(true);
        modifersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
        System.out.println(field);
        field.set(obj, newValue);
    }

    public static void main(String[] args) throws Exception {
//        ChangeStaticFinalFieldSample2 changeStaticFinalFieldSample2 = new ChangeStaticFinalFieldSample2();
        Sample2 sample2 = new Sample2();
        sample2.print();
        Field canChangeField = Sample2.class.getDeclaredField("CAN_CHANGE");
        Field cannotChangeField = Sample2.class.getDeclaredField("CANNOT_CHANGE");
        Field str = Sample2.class.getDeclaredField("STR");
        Field strChange = Sample2.class.getDeclaredField("STR_CHANGE");
        changeStaticFinal(canChangeField, 2, sample2);
        changeStaticFinal(cannotChangeField, 3,sample2);
        changeStaticFinal(str, "big",sample2);
        changeStaticFinal(strChange, "bigger",sample2);

        sample2.print();

    }
}

class Sample2 {
    private  final int CAN_CHANGE = new Integer(200);
    private  final int CANNOT_CHANGE = 200;
    private  final String STR = "small";
    private  final String STR_CHANGE = new String("small");

    public  void print() {
        System.out.println("CAN_CHANGE = " + CAN_CHANGE);
        System.out.println("CANNOT_CHANGE = " + CANNOT_CHANGE);
        System.out.println("String = " + STR);
        System.out.println("String = " + STR_CHANGE);
        System.out.println("------------------------");
    }

}

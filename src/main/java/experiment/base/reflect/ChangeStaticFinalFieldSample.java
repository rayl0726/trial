package experiment.base.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @author : liulei
 **/
public class ChangeStaticFinalFieldSample {

    static void changeStaticFinal(Field field, Object newValue) throws Exception {
        field.setAccessible(true);

        Field modifersField = Field.class.getDeclaredField("modifiers");
        modifersField.setAccessible(true);
        modifersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

        field.set(null, newValue);
    }

    public static void main(String[] args) throws Exception {
        Sample.print();

        Field canChangeField = Sample.class.getDeclaredField("CAN_CHANGE");
        Field cannotChangeField = Sample.class.getDeclaredField("CANNOT_CHANGE");
        Field str = Sample.class.getDeclaredField("STR");
        Field strChange = Sample.class.getDeclaredField("STR_CHANGE");
        changeStaticFinal(canChangeField, 2);
        changeStaticFinal(cannotChangeField, 3);
        changeStaticFinal(str, "big");
        changeStaticFinal(strChange, "bigger");

        Sample.print();

    }

}

class Sample {
    private static final int CAN_CHANGE = new Integer(200);
    private static final int CANNOT_CHANGE = 200;
    private static final String STR = "small";
    private static final String STR_CHANGE = new String("small");

    public static void print() {
        System.out.println("CAN_CHANGE = " + CAN_CHANGE);
        System.out.println("CANNOT_CHANGE = " + CANNOT_CHANGE);
        System.out.println("String = " + STR);
        System.out.println("String = " + STR_CHANGE);
        System.out.println("------------------------");
    }

}


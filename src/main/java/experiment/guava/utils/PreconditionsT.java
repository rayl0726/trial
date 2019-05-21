package experiment.guava.utils;

import static com.google.common.base.Preconditions.*;

/**
 * @author : liulei
 **/
public class PreconditionsT {
    private String label;
    private int[] values = new int[5];
    private int currentIndex;

    public PreconditionsT(String label) {
        //returns value of object if not null
        this.label = checkNotNull(label,"Label can''t be null");
    }

    public void updateCurrentIndexValue(int index, int valueToSet) {
        //Check index valid first
        this.currentIndex = checkElementIndex(index, values.length, "Index out of bounds for values");
        //Validate valueToSet
        checkArgument(valueToSet <= 100,"Value can't be more than 100");
        this.values[this.currentIndex] = valueToSet;
    }

    public void doOperation(){
        checkState(validateObjectState(),"Can't perform operation");
    }

    private boolean validateObjectState(){
        return this.label.equalsIgnoreCase("open") && values[this.currentIndex]==10;
    }


    public static void main(String[] args) {
        PreconditionsT preconditionsT = new PreconditionsT("open");
        preconditionsT.updateCurrentIndexValue(1, 10);
        preconditionsT.doOperation();
    }
}

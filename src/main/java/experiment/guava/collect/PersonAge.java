package experiment.guava.collect;

import com.google.common.primitives.Ints;

import java.util.Comparator;

/**
 * @author : liulei
 **/
public class PersonAge implements Comparator<Person> {
    @Override
    public int compare(Person o1, Person o2) {
        return Ints.compare(o1.getAge(),o2.getAge());
    }
}

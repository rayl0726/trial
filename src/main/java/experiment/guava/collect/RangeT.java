package experiment.guava.collect;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Range;

/**
 * @author : liulei
 **/
public class RangeT {
    public static void main(String[] args) {
//        //闭区间，包含边界条件
//        Range<Integer> numRange = Range.closed(1,10);
//        boolean contains10 = numRange.contains(10);
//        boolean contains1 = numRange.contains(1);
//        //开区间，不包含边界条件
//        Range<Integer> numRange2 = Range.open(1,10);
//        boolean contain10 = numRange2.contains(10);
//        boolean contain1 = numRange2.contains(1);

        Range<Integer> ageRange = Range.closed(35,50);
        Function<Person,Integer> ageFunction = new Function<Person, Integer>() {
            @Override
            public Integer apply(Person person) {
                return person.getAge();
            }
        };

        Predicate<Person> predicate = Predicates.compose(ageRange, ageFunction);
        Person person = new Person();
        person.setAge(40);
        System.out.println(predicate.test(person));

    }
}

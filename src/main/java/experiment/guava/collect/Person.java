package experiment.guava.collect;

import com.google.common.collect.ComparisonChain;

/**
 * @author : liulei
 **/
public class Person implements Comparable<Person> {
    private String firstName;
    private String lastName;
    private int age;
    private String sex;

    @Override
    public int compareTo(Person o) {
        return ComparisonChain.start().
                compare(this.firstName,o.firstName).
                compare(this.lastName,o.lastName).
                compare(this.age,o.age).
                compare(this.sex,o.sex).result();
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}

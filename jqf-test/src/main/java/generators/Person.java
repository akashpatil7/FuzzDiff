package generators;

import java.io.Serializable;
import java.util.Arrays;

public class Person implements Serializable {
    private String name;
    private int age;
    private int[] phones;

    public Person(){

    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getPhones() {
        return phones;
    }

    public void setPhones(int[] phones) {
        this.phones = phones;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", phones=" + Arrays.toString(phones) +
                '}';
    }


}

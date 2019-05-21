package setCompare;

import setCompare.pojo.Person;

import java.util.HashMap;
import java.util.Map;

public class HashSetCompareTest {


    public static void main(String[] args) {

        Map<Person, Integer> personHashMap = new HashMap<Person, Integer>();
        Person person1 = new Person("1", "Jay", 30);
        Person person2 = new Person("2", "Jolin", 21);
        Person person3 = new Person("3", "Jack Cheng", 22);
        Person person4 = new Person("4", "Bruce Lee", 22);
        personHashMap.put(person1, 100);
        personHashMap.put(person2, 200);
        personHashMap.put(person3, 300);
        personHashMap.put(person4, 400);

        System.out.println(personHashMap);
    }

}

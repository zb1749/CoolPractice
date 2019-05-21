package setCompare;

import setCompare.pojo.ReWriteEqualsOnlyPojo;
import setCompare.pojo.ReWriteHashCodeAndEqualsPojo;

import java.util.*;

public class SetAndMapCompare {
    public static void main(String[] args) {
        Map<ReWriteHashCodeAndEqualsPojo, Integer> hashMap1 = new HashMap<ReWriteHashCodeAndEqualsPojo, Integer>();
        ReWriteHashCodeAndEqualsPojo p1 = new ReWriteHashCodeAndEqualsPojo("1", "Jay", 30);
        ReWriteHashCodeAndEqualsPojo p2 = new ReWriteHashCodeAndEqualsPojo("1", "Lucy", 30);
        hashMap1.put(p1,1);
        hashMap1.put(p2,2);
        System.out.println("HashMap");
        System.out.println(hashMap1);
        //{Person [name=Jay, age=30]=2}
        //很奇怪，hashmap里的key对象需要重写的equals方法，并且，如果key比较相等，则不更新key，更新value。
        //嗯，看HashMap.put的源码就都知道了=。=

        Set<ReWriteHashCodeAndEqualsPojo> hashSet1 = new HashSet<ReWriteHashCodeAndEqualsPojo>();
        hashSet1.add(p1);
        hashSet1.add(p2);
        System.out.println("HashSet");
        System.out.println(hashSet1);
        //[Person [name=Jay, age=30]]
        //看源码就知道了，HashSet.add方法只是封装了HashMap.put

        Map<ReWriteEqualsOnlyPojo, Integer> hashMap2 = new HashMap<ReWriteEqualsOnlyPojo, Integer>();
        ReWriteEqualsOnlyPojo pe1 = new ReWriteEqualsOnlyPojo("1", "Jay", 30);
        ReWriteEqualsOnlyPojo pe2 = new ReWriteEqualsOnlyPojo("1", "Lucy", 30);
        hashMap2.put(pe1,1);
        hashMap2.put(pe2,2);
        System.out.println("HashMap");
        System.out.println(hashMap1);
        //{Person [name=Jay, age=30]=2}
        //所以，可以看出HashMap的key去重判断，只需要重写equals方法就可以了

        Set<ReWriteEqualsOnlyPojo> hashSet2 = new HashSet<ReWriteEqualsOnlyPojo>();
        hashSet2.add(pe1);
        hashSet2.add(pe2);
        System.out.println("HashSet");
        System.out.println(hashSet2);
        //[Person [name=Jay, age=30], Person [name=Lucy, age=30]]
        //奇怪不。明明只是封装了HashMap.put，结果HashSet不重写hashCode无法去重，而HashMap却可以=。=

        Map<ReWriteHashCodeAndEqualsPojo, Integer>  treeMap1 = new TreeMap<ReWriteHashCodeAndEqualsPojo, Integer>();
        treeMap1.put(p1,1);
        treeMap1.put(p2,2);
        System.out.println("TreeMap");
        System.out.println(treeMap1);
        //java.lang.ClassCastException: setCompare.pojo.ReWriteHashCodeAndEqualsPojo cannot be cast to java.lang.Comparable
        //at java.util.TreeMap.compare(TreeMap.java:1294)
        //so，TreeMap和TreeSet必须传入Comparator，需要排序嘛=。=
        //C


    }
}

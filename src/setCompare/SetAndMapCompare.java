package setCompare;

import org.apache.log4j.Logger;
import setCompare.pojo.*;

import java.util.*;

public class SetAndMapCompare {
    private static Logger logger = Logger.getLogger(SetAndMapCompare.class);

    public static void main(String[] args) {

        Map<ReWriteHashCodeAndEqualsPojo, Integer> hashMap1 = new HashMap<ReWriteHashCodeAndEqualsPojo, Integer>();
        ReWriteHashCodeAndEqualsPojo p1 = new ReWriteHashCodeAndEqualsPojo("1", "Jay", 30);
        ReWriteHashCodeAndEqualsPojo p2 = new ReWriteHashCodeAndEqualsPojo("1", "Lucy", 30);
        hashMap1.put(p1, 1);
        hashMap1.put(p2, 2);
        System.out.println("ReWriteHashCodeAndEquals HashMap");
        System.out.println(hashMap1);
        //{Person [name=Jay, age=30]=2}
        //很奇怪，hashmap里的key对象需要重写的equals方法，并且，如果key比较相等，则不更新key，更新value。
        //嗯，看HashMap.put的源码就都知道了=。=

        //hashmap的key可以为null

        Set<ReWriteHashCodeAndEqualsPojo> hashSet1 = new HashSet<ReWriteHashCodeAndEqualsPojo>();
        hashSet1.add(p1);
        hashSet1.add(p2);
        System.out.println("ReWriteHashCodeAndEquals HashSet");
        System.out.println(hashSet1);
        //[Person [name=Jay, age=30]]


        Map<ReWriteEqualsOnlyPojo, Integer> hashMap2 = new HashMap<ReWriteEqualsOnlyPojo, Integer>();
        ReWriteEqualsOnlyPojo pe1 = new ReWriteEqualsOnlyPojo("1", "Jay", 30);
        ReWriteEqualsOnlyPojo pe2 = new ReWriteEqualsOnlyPojo("1", "Lucy", 30);
        hashMap2.put(pe1, 1);
        hashMap2.put(pe2, 2);
        System.out.println("ReWriteEqualsOnly HashMap");
        System.out.println(hashMap1);
        //{Person [name=Jay, age=30]=2}
        //所以，可以看出HashMap的key去重判断，只需要重写equals方法就可以了

        Set<ReWriteEqualsOnlyPojo> hashSet2 = new HashSet<ReWriteEqualsOnlyPojo>();
        hashSet2.add(pe1);
        hashSet2.add(pe2);
        System.out.println("ReWriteEqualsOnly HashSet");
        System.out.println(hashSet2);
        //[Person [name=Jay, age=30], Person [name=Lucy, age=30]]
        //奇怪不。明明只是封装了HashMap.put，结果HashSet不重写hashCode无法去重，而HashMap却可以=。=
        /**
         public V put(K key, V value) {
         return putVal(hash(key), key, value, false, true);
         }

         static final int hash(Object key) {
         int h;
         return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
         }
         */

        Map<ReWriteHashCodeAndEqualsPojo, Integer> treeMap1 = new TreeMap<ReWriteHashCodeAndEqualsPojo, Integer>();
        try {
            treeMap1.put(p1, 1);
            treeMap1.put(p2, 2);
        } catch (Exception e) {
            logger.error("treeMap1.put error: ", e);
        }
        System.out.println("TreeMap");
        System.out.println(treeMap1);
        //java.lang.ClassCastException: setCompare.pojo.ReWriteHashCodeAndEqualsPojo cannot be cast to java.lang.Comparable
        //at java.util.TreeMap.compare(TreeMap.java:1294)
        //at java.util.TreeMap.put(TreeMap.java:538)

        //so，TreeMap和TreeSet必须在初始化的时候传入Comparator，需要排序嘛=。=（没有setComparator这种方法，所以，必须在初始化设置）
        //如果不传入Comparator，则传入的key必须可以强制转换为Comparable类型（即这种情况下，key类需要实现Comparable接口）
        //TreeMap.put调用的compare源码中可以看到
        //final int compare(Object k1, Object k2) {
        //return comparator==null ? ((Comparable<? super K>)k1).compareTo((K)k2) : comparator.compare((K)k1, (K)k2);
        //}

        //TreeMap 底层为数据结构为红黑树，默认为升序排序方式。
        //整个红黑树的结构为：根节点值大于所有左子树节点值，小于所有右子树节点值，由此整个红黑树以深度优先搜索方式遍历一遍为从小到大的升序排列。

        //TreeMap key值可以为null，但是需要传入Comparator，并且compare方法内做null判断，否则会抛错！
        //1)当key未实现Comparator接口时，key不可以为null，否则抛NullPointerException异常。(源码直接check，然后手动抛的异常。)
        //2)当key实现了Comparator接口时，若实现类的compare未对null情况进行判断，则加入null值会抛NullPointerException异常。


        Set<ReWriteEqualsOnlyPojo> treeSet1 = new TreeSet<ReWriteEqualsOnlyPojo>();
        try {
            treeSet1.add(pe1);
            treeSet1.add(pe2);
        } catch (Exception e) {
            logger.error("treeSet1.add error: ", e);
        }
        System.out.println("TreeSet");
        System.out.println(treeSet1);

        //验证继承comparable强转比较的
        ComparablePojo pc1 = new ComparablePojo(1, "Jay", 30);
        ComparablePojo pc2 = new ComparablePojo(1, "Lucy", 30);
        ComparablePojo pc3 = new ComparablePojo(1, "Ferry", 55);
        ComparablePojo pc4 = new ComparablePojo(2, "Nana", 30);
        ComparablePojo pc5 = new ComparablePojo(3, "Hurry", 55);

        Map<ComparablePojo, Integer> treeMap2 = new TreeMap<ComparablePojo, Integer>();
        try {
            treeMap2.put(pc1, 1);
            treeMap2.put(pc2, 2);
            treeMap2.put(pc3, 3);
        } catch (Exception e) {
            logger.error("treeMap1.put error: ", e);
        }
        System.out.println("TreeMap");
        System.out.println(treeMap2);
        //{Person [name=Jay, age=30]=2, Person [name=Ferry, age=55]=3}
        //这里看出来，treeMap里的元素，通过compareTo比较相等，并不会替换key，而value不同会更新，这点同hashmap一样


        Set<ComparablePojo> treeSet2 = new TreeSet<ComparablePojo>();
        try {
            treeSet2.add(pc1);
            treeSet2.add(pc3);
            treeSet2.add(pc2);
        } catch (Exception e) {
            logger.error("treeSet1.add error: ", e);
        }
        System.out.println("TreeSet");
        System.out.println(treeSet2);
        //[Person [name=Jay, age=30], Person [name=Ferry, age=55]]
        //很显然，可以看出treemap和treeset是排序，不过不是根据value排序，而是根据key，嗯，思密达=。=

        //验证comparator优先于comparable，显然treemap源码的compare方法里面可以看到=。=
        PersonComparator noNullCheckComparator = new PersonComparator();
        Map<ComparablePojo, Integer> treeMap3 = new TreeMap<ComparablePojo, Integer>(noNullCheckComparator);
        treeMap3.put(pc1, 1);
        treeMap3.put(pc2, 2);
        treeMap3.put(pc3, 3);
        treeMap3.put(pc4, 4);
        treeMap3.put(pc5, 5);
        System.out.println("TreeMap");
        System.out.println(treeMap3);
        //{Person [id=1, name=Jay, age=30]=3, Person [id=2, name=Nana, age=30]=4, Person [id=3, name=Hurry, age=55]=5}
        //可以看出，是使用id去重的，并使用id升序排序的。这可以看出，comparator优先于comparable

        Set<ComparablePojo> treeSet3 = new TreeSet<ComparablePojo>(noNullCheckComparator);
        try {
            //treeSet3.add(null);
            treeSet3.add(pc1);
            treeSet3.add(pc2);
            treeSet3.add(pc3);
            treeSet3.add(pc4);
            treeSet3.add(pc5);
        } catch (Exception e) {
            logger.error("treeSet3.add error: ", e);
        }
        System.out.println("TreeSet");
        System.out.println(treeSet3);
        //[Person [id=1, name=Jay, age=30], Person [id=2, name=Nana, age=30], Person [id=3, name=Hurry, age=55]]
        /**
         treeSet3.add(null);
         treeSet3.add(pc1);

         treeSet3.add error:
         java.lang.NullPointerException
         at setCompare.pojo.PersonComparator.compare(PersonComparator.java:16)
         at setCompare.pojo.PersonComparator.compare(PersonComparator.java:5)
         at java.util.TreeMap.compare(TreeMap.java:1295)
         at java.util.TreeMap.put(TreeMap.java:538)
         at java.util.TreeSet.add(TreeSet.java:255)
         at setCompare.SetAndMapCompare.main(SetAndMapCompare.java:143)
         */
        //这里可以看出，使用了comparator，如果compare方法内不做null判断，会抛出nullpointer异常=。= 嗯，这个不奇怪，不算一个考点=。=

        NullCheckComparator nullCheckComparator = new NullCheckComparator();

        Map<ComparablePojo, Integer> treeMap4 = new TreeMap<ComparablePojo, Integer>(nullCheckComparator);
        try {
            treeMap4.put(null, 0);
            treeMap4.put(pc1, 1);
            treeMap4.put(pc2, 2);
            treeMap4.put(pc3, 3);
            treeMap4.put(pc4, 4);
            treeMap4.put(pc5, 5);
        } catch (Exception e) {
            logger.error("treeMap4.put error: ", e);
        }
        System.out.println("TreeMap");
        System.out.println(treeMap4);
        //{null=0, Person [id=1, name=Jay, age=30]=3, Person [id=2, name=Nana, age=30]=4, Person [id=3, name=Hurry, age=55]=5}


        Set<ComparablePojo> treeSet4 = new TreeSet<ComparablePojo>(nullCheckComparator);
        try {
            treeSet4.add(null);
            treeSet4.add(pc1);
            treeSet4.add(pc2);
            treeSet4.add(pc3);
            treeSet4.add(pc4);
            treeSet4.add(pc5);
        } catch (Exception e) {
            logger.error("treeSet4.add error: ", e);
        }
        System.out.println("TreeSet");
        System.out.println(treeSet4);
        //[null, Person [id=1, name=Jay, age=30], Person [id=2, name=Nana, age=30], Person [id=3, name=Hurry, age=55]]
        //事实证明，root跟也可以是null

        //这几个集合，真他娘的有一坨乱七八糟的小细节，真烦=。=
        //难么，不难，就是烦=。=
    }
}

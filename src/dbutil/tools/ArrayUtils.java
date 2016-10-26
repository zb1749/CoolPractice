package dbutil.tools;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import dbutil.tools.depend.ArraySimpleToStringStyle;
import dbutil.tools.depend.EnumerationWrapper;


/**
 * 数组工具
 */
public class ArrayUtils extends org.apache.commons.lang.ArrayUtils {
    /**
     * 数组对象遍历执行toString方法，获得String数组对象。
     *
     * @param list
     * @return
     */
    public static String[] toStringArray(List<? extends Object> list) {
        List<String> result = new ArrayList<String>();
        for (Object f : list) {
            result.add(f.toString());
        }
        return result.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
    }

    /**
     * 将Enumeration包装成符合Iterable的对象
     *
     * @param <T>
     * @param e
     * @return
     */
    public static <T> Iterable<T> toIterable(Enumeration<T> e) {
        return new EnumerationWrapper<T>(e);
    }

    @SuppressWarnings("unchecked")
    public static <T> T[] toArray(Enumeration<T> e, Class<T> type) {
        List<T> result = new ArrayList<T>();
        for (; e.hasMoreElements(); ) {
            result.add(e.nextElement());
        }
        return result.toArray((T[]) Array.newInstance(type, result.size()));
    }

    /**
     * 将CharSequence变为可遍历的char对象
     * 这样就可以对CharBuffer,StringBuilder,Stringbuffer等对象进行Iterator模式的遍历了。
     *
     * @param e
     * @return
     */
    public static Iterable<Character> toIterable(final CharSequence e) {
        return new Iterable<Character>() {
            public Iterator<Character> iterator() {
                return new Iterator<Character>() {
                    int n = 0;

                    public boolean hasNext() {
                        return n < e.length();
                    }

                    public Character next() {
                        return e.charAt(n++);
                    }


                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }

    /**
     * 将一个子类的数组转换为父类的数组
     *
     * @param from
     * @param to
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <S, T> T[] cast(S[] from, Class<T> to) {
        T[] result = (T[]) Array.newInstance(to, from.length);
        for (int i = 0; i < result.length; i++) {
            result[i] = (T) from[i];
        }
        return result;
    }

    /**
     * 从数组中移除null的元素
     *
     * @param <T>
     * @param arr1
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] removeNull(T[] arr1) {
        List<T> list = new ArrayList<T>(arr1.length);
        for (T e : arr1) {
            if (e != null) {
                list.add(e);
            }
        }
        if (list.size() == arr1.length) return arr1;
        //此处不能使用 list.toArray(arr1);
        //因为ArrayList.toArray[]的实现是将List元素拷贝到给出的容器中，如果容器大于List的空间，则在超出部分补上null.
        //因此不能起到消除null元素的作用。
        T[] t = (T[]) Array.newInstance(arr1.getClass().getComponentType(), list.size());
        return list.toArray(t);
    }

    /**
     * 将原生八类型的数组容器转换为对象八类型数组
     *
     * @param obj
     * @return
     */
    public static Object[] toObject(Object obj) {
        Class<?> c = obj.getClass();
        //Assert.isTrue(c.isArray());
        Class<?> priType = c.getComponentType();
        if (!priType.isPrimitive()) return (Object[]) obj;
        if (priType == Boolean.TYPE) {
            return toObject((boolean[]) obj);
        } else if (priType == Byte.TYPE) {
            return toObject((byte[]) obj);
        } else if (priType == Character.TYPE) {
            return toObject((char[]) obj);
        } else if (priType == Integer.TYPE) {
            return toObject((int[]) obj);
        } else if (priType == Long.TYPE) {
            return toObject((long[]) obj);
        } else if (priType == Float.TYPE) {
            return toObject((float[]) obj);
        } else if (priType == Double.TYPE) {
            return toObject((double[]) obj);
        } else if (priType == Short.TYPE) {
            return toObject((short[]) obj);
        }
        throw new IllegalArgumentException();
    }


    /**
     * 将由非原生八类型的数组转换为原生八类型的数组
     */
    public static Object toPrimitive(Object[] obj) {
        Class<?> c = obj.getClass();
        //Assert.isTrue(c.isArray());
        Class<?> objType = c.getComponentType();
        if (objType == Boolean.class) {
            return toPrimitive((Boolean[]) obj);
        } else if (objType == Byte.class) {
            return toPrimitive((Byte[]) obj);
        } else if (objType == Character.class) {
            return toPrimitive((Character[]) obj);
        } else if (objType == Integer.class) {
            return toPrimitive((Integer[]) obj);
        } else if (objType == Long.class) {
            return toPrimitive((Long[]) obj);
        } else if (objType == Float.class) {
            return toPrimitive((Float[]) obj);
        } else if (objType == Double.class) {
            return toPrimitive((Double[]) obj);
        } else if (objType == Short.class) {
            return toPrimitive((Short[]) obj);
        } else {
            throw new IllegalArgumentException();
        }
    }


    /**
     * 合并两个数组，消除重复的元素
     *
     * @param <T>
     * @param array1
     * @param array2
     * @return
     */
    public static <T> T[] merge(T[] array1, T[] array2) {
        List<T> list = new ArrayList<T>();
        for (T str : array1) {
            list.add(str);
        }
        for (T str : array2) {
            if (!list.contains(str)) {
                list.add(str);
            }
        }
        return list.toArray(array1);
    }

    /**
     * 合并数组，消除重复
     */
    public static int[] merge(int[] array1, int[] array2) {
        List<Integer> list = new ArrayList<Integer>();
        for (int str : array1) {
            list.add(str);
        }
        for (int str : array2) {
            if (!list.contains(str)) {
                list.add(str);
            }
        }
        return toPrimitive(list.toArray(new Integer[]{}));
    }

    /**
     * 合并数组，去掉重复
     */
    public static char[] merge(char[] array1, char[] array2) {
        List<Character> list = new ArrayList<Character>();
        for (char str : array1) {
            list.add(str);
        }

        for (char str : array2) {
            if (!list.contains(str)) {
                list.add(str);
            }
        }
        return toPrimitive(list.toArray(new Character[]{}));
    }

    /**
     * 合并数组，去掉重复
     */
    public static double[] merge(double[] array1, double[] array2) {
        List<Double> list = new ArrayList<Double>();
        for (double str : array1) {
            list.add(str);
        }

        for (double str : array2) {
            if (!list.contains(str)) {
                list.add(str);
            }
        }
        return toPrimitive(list.toArray(new Double[]{}));
    }

    /**
     * 合并数组，去掉重复
     */
    public static boolean[] merge(boolean[] array1, boolean[] array2) {
        List<Boolean> list = new ArrayList<Boolean>();
        for (boolean str : array1) {
            list.add(str);
        }

        for (boolean str : array2) {
            if (!list.contains(str)) {
                list.add(str);
            }
        }
        return toPrimitive(list.toArray(new Boolean[]{}));
    }

    /**
     * 合并数组，去掉重复
     */
    public static long[] merge(long[] array1, long[] array2) {
        List<Long> list = new ArrayList<Long>();
        for (long str : array1) {
            list.add(str);
        }

        for (long str : array2) {
            if (!list.contains(str)) {
                list.add(str);
            }
        }
        return toPrimitive(list.toArray(new Long[]{}));
    }

    /**
     * 合并数组，去除重复
     */
    public static byte[] merge(byte[] array1, byte[] array2) {
        List<Byte> list = new ArrayList<Byte>();
        for (byte str : array1) {
            list.add(str);
        }

        for (byte str : array2) {
            if (!list.contains(str)) {
                list.add(str);
            }
        }
        return toPrimitive(list.toArray(new Byte[]{}));
    }

    /**
     * 去掉重复数据
     */
    public static int[] removeDups(int[] array) {
        List<Integer> list = new ArrayList<Integer>();
        for (int str : array) {
            if (!list.contains(str))
                list.add(str);
        }
        return toPrimitive(list.toArray(new Integer[]{}));

    }

    /**
     * 去掉重复数据
     */
    public static char[] removeDups(char[] array) {
        List<Character> list = new ArrayList<Character>();
        for (char str : array) {
            if (!list.contains(str))
                list.add(str);
        }
        return toPrimitive(list.toArray(new Character[]{}));
    }

    /**
     * 去掉重复数据
     */
    public static byte[] removeDups(byte[] array) {
        List<Byte> list = new ArrayList<Byte>();
        for (byte str : array) {
            if (!list.contains(str))
                list.add(str);
        }
        return toPrimitive(list.toArray(new Byte[]{}));
    }

    /**
     * 去掉重复数据
     */
    public static double[] removeDups(double[] array) {
        List<Double> list = new ArrayList<Double>();
        for (double str : array) {
            if (!list.contains(str))
                list.add(str);
        }
        return toPrimitive(list.toArray(new Double[]{}));

    }

    /**
     * 去掉重复数据
     */
    public static boolean[] removeDups(boolean[] array) {
        List<Boolean> list = new ArrayList<Boolean>();
        for (boolean str : array) {
            if (!list.contains(str))
                list.add(str);
        }
        return toPrimitive(list.toArray(new Boolean[]{}));
    }

    /**
     * 去掉重复数据
     */
    public static <T> T[] removeDups(T[] array) {
        List<T> list = new ArrayList<T>();
        for (T str : array) {
            if (!list.contains(str))
                list.add(str);
        }
        return list.toArray(array);
    }

    /**
     * 包含任意一个元素
     */
    public static <T> boolean containsAny(T[] otherContains, T[] formats) {
        for (T obj1 : otherContains) {
            for (T obj2 : formats) {
                if (obj1.equals(obj2))
                    return true;
            }
        }
        return false;
    }

    public static <T> boolean notContains(T[] array, T obj) {
        return !contains(array, obj);
    }

    public static boolean notContains(long[] array, long obj) {
        return !contains(array, obj);
    }

    public static boolean notContains(short[] array, short obj) {
        return !contains(array, obj);
    }

    public static boolean notContains(char[] array, char obj) {
        return !contains(array, obj);
    }

    public static boolean notContains(double[] array, double obj) {
        return !contains(array, obj);
    }

    public static boolean notContains(float[] array, float obj) {
        return !contains(array, obj);
    }

    public static boolean notContains(byte[] array, byte obj) {
        return !contains(array, obj);
    }

    public static boolean notContains(int[] array, int obj) {
        return !contains(array, obj);
    }

    /**
     * 泛型的addArray
     * apache默认的add方法居然不支持泛型...怨念...
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] addElement(T[] array, T data, Class<T> componentType) {
        if (data == null) return array;
        T[] newArray;
        if (array == null) {
            //Assert.notNull(componentType, "The componentType shoule be assigned when the array is null.");
            newArray = (T[]) Array.newInstance(componentType, 1);
            newArray[0] = data;
        } else {
            Class<?> containerType = array.getClass().getComponentType();
            if (!containerType.isAssignableFrom(data.getClass())) {//prompt the type error.
                throw new ArrayStoreException("The new element which typed " + data.getClass().getName() + " can not be put into a array whoes type is " + containerType.getName());
            }
            newArray = (T[]) Array.newInstance(containerType, array.length + 1);
            System.arraycopy(array, 0, newArray, 0, array.length);
            newArray[array.length] = data;
        }
        return newArray;
    }

    @SuppressWarnings("unchecked")
    public static <T> T[] addElement(T[] array, T element) {
        if (element == null) return array;
        return addElement(array, element, (Class<T>) element.getClass());
    }

    @SuppressWarnings("unchecked")
    public static <T> T[] addAllElement(T[] array, T[] data) {
        if (data == null || data.length == 0) return array;
        T[] newArray;
        if (array == null) {
            return data;
        } else {
            newArray = (T[]) Array.newInstance(data.getClass().getComponentType(), array.length + data.length);
            System.arraycopy(array, 0, newArray, 0, array.length);
            System.arraycopy(data, 0, newArray, array.length, data.length);
        }
        return newArray;
    }

    /**
     * 获取子串
     *
     * @param array
     * @param len
     * @return
     */
    public static byte[] subArray(byte[] array, int len) {
        if (array.length == len) {
            return array;
        } else if (len > array.length) {
            len = array.length;
        }
        byte[] data = new byte[len];
        System.arraycopy(array, 0, data, 0, len);
        return data;
    }


    /**
     * 泛型的subArray.如果使用非泛型的方法，a小写
     *
     * @param <T>
     * @param array
     * @param startIndexInclusive
     * @param endIndexExclusive
     * @return
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static <T> T[] subArray(T[] array, int startIndexInclusive, int endIndexExclusive) {
        if (array == null) {
            return null;
        }
        if (startIndexInclusive < 0) {
            startIndexInclusive = 0;
        }
        if (endIndexExclusive > array.length) {
            endIndexExclusive = array.length;
        }
        int newSize = endIndexExclusive - startIndexInclusive;
        Class type = array.getClass().getComponentType();
        if (newSize <= 0) {
            return (T[]) Array.newInstance(type, 0);
        }
        T[] subarray = (T[]) Array.newInstance(type, newSize);
        System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
        return subarray;
    }

    /**
     * 取得数组当中的某几号元素，重新组成数组
     */
    public static <T> List<T> subByIndex(List<T> list, int[] indexes) {
        List<T> newList = new ArrayList<T>();
        for (int i : indexes) {
            newList.add(list.get(i));
        }
        return newList;
    }

    /**
     * 判断列表中是否包含指定的对象,和Collection.contains方法比起来，前者是用obj1.equals(obj2)，
     * 这里用==直接判断是否<B>同一对象</B>，速度更快，但是不能比较出两个值完全相同的对象来。
     *
     * @param <T>
     * @param list
     * @param obj
     * @return
     */
    public static <T> boolean fastContains(T[] list, T obj) {
        if (list == null) return false;
        for (T e : list) {
            if (e == obj)
                return true;
        }
        return false;
    }

    /**
     * 判断列表中是否包含指定的对象,和Collection.contains方法比起来，前者是用obj1.equals(obj2)，
     * 这里用==直接判断是否<B>同一对象</B>，速度更快，但是不能比较出两个值完全相同的对象来。
     *
     * @param <T>
     * @param list
     * @param obj
     * @return
     */
    public static <T> boolean fastContains(Collection<T> list, T obj) {
        for (T e : list) {
            if (e == obj)
                return true;
        }
        return false;
    }

    public static <T> boolean fastContainsAny(Collection<T> list, T[] keys) {
        for (T e : list) {
            for (T obj : keys) {
                if (e == obj)
                    return true;
            }
        }
        return false;
    }

    public static <T> boolean fastContainsAny(T[] list, T[] keys) {
        for (T e : list) {
            for (T obj : keys) {
                if (e == obj)
                    return true;
            }
        }
        return false;
    }

    /**
     * 同Arrays.asList方法,但实现类功能较强
     */
    public static <T> List<T> asList(T... args) {
        ArrayList<T> list = new ArrayList<T>(args.length + 16);
        list.addAll(Arrays.asList(args));
        return list;
    }

    /**
     * 同Arrays.asList方法,但实现类功能较强
     */
    public static <T, X extends T> List<T> asTypedList(Class<T> clz, X... args) {
        ArrayList<T> list = new ArrayList<T>();
        list.addAll(Arrays.asList(args));
        return list;
    }


    /**
     * 计算是否 包含目标子串，忽略大小写
     *
     * @param values
     * @param str
     * @return
     */
    public static boolean containsIgnoreCase(String[] values, String str) {
        for (String v : values) {
            if (v == null) {
                if (str == null) {
                    return true;
                } else {
                    continue;
                }
            }
            if (v.equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 转换为指定长度的数组,超过则截断，不足则补null
     */
    public static Object toFixLength(Object obj, int len) {
        int length = length(obj);
        if (length == len) return obj;
        Object result = Array.newInstance(obj.getClass().getComponentType(), len);
        System.arraycopy(obj, 0, result, 0, Math.min(length, len));
        return result;
    }

    public interface Filter<T> {
        boolean accept(T o);
    }

    /**
     * 从可枚举对象中选出需要的目标组成新的List
     *
     * @param <T>
     * @param list
     * @param filter
     * @return
     */
    public static <T> List<T> doSelect(Iterable<T> list, Filter<T> filter) {
        ArrayList<T> result = new ArrayList<T>();
        for (T o : list) {
            if (filter.accept(o)) {
                result.add(o);
            }
        }
        return result;
    }

    /**
     * @Since JDK 1.6
     */
    public static byte[] copyOf(byte[] original, int newLength) {
        byte[] copy = new byte[newLength];
        System.arraycopy(original, 0, copy, 0,
                Math.min(original.length, newLength));
        return copy;
    }

    /**
     * @since JDK 1.6
     */
    public static char[] copyOf(char[] original, int newLength) {
        char[] copy = new char[newLength];
        System.arraycopy(original, 0, copy, 0,
                Math.min(original.length, newLength));
        return copy;
    }

    /**
     * @since JDK 1.6
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] copyOf(T[] original, int newLength) {
        return (T[]) copyOf(original, newLength, original.getClass());
    }

    /**
     * @since JDK 1.6
     */
    @SuppressWarnings("unchecked")
    public static <T, U> T[] copyOf(U[] original, int newLength, Class<? extends T[]> newType) {
        T[] copy = ((Object) newType == (Object) Object[].class)
                ? (T[]) new Object[newLength]
                : (T[]) Array.newInstance(newType.getComponentType(), newLength);
        System.arraycopy(original, 0, copy, 0,
                Math.min(original.length, newLength));
        return copy;
    }

    /**
     * 两个对象数组的比较
     */
    public static boolean equals(Object[] a1, Object[] a2) {
        if (a1 == null && a2 == null) return true;
        if (a1 == null || a2 == null) return false;
        if (a1.length != a2.length) return false;
        for (int n = 0; n < a1.length; n++) {
            if (!ObjectUtils.equals(a1[n], a2[n])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断两个数组，忽略其顺序
     *
     * @param a1
     * @param a2
     * @return
     */
    public static <T> boolean equalsIgnorSort(T[] a1, T[] a2) {
        if (a1 == null && a2 == null) return true;
        if (a1 == null || a2 == null) return false;
        if (a1.length != a2.length) return false;
        for (int n = 0; n < a1.length; n++) {
            T o = a1[n];
            if (!contains(a2, o)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 操作未知类型的数组:获取数组长度。
     */
    public static int length(Object obj) {
        return Array.getLength(obj);
    }

    /**
     * 操作未知类型的数组:set
     * 当序号为负数时，-1表示最后一个元素，-2表示倒数第二个，以此类推
     * 和set方法的区别在于，此方法如果发现数组大小不够，会自动扩大数组。
     */
    public static Object setValueAndExpandArray(Object obj, int index, Object value) {
        int length = length(obj);
        Object result = obj;
        if (index < 0 && index + length >= 0) {
            index += length;
        } else if (index < 0) {//需要扩张
            result = toFixLength(obj, -index);
        } else if (index >= length) {//扩张
            result = toFixLength(obj, index + 1);
        }
        set(result, index, value);
        return result;
    }

    /**
     * 检测索引是否有效
     * 当序号为负数时，-1表示最后一个元素，-2表示倒数第二个，以此类推
     */
    public static boolean isIndexValid(Object obj, int index) {
        int length = length(obj);
        if (index < 0) index += length;
        return index >= 0 && index < length;
    }

    /**
     * 操作未知类型的数组:get
     *
     * @param obj   数组对象
     * @param index 序号
     *              当序号为负数时，-1表示最后一个元素，-2表示倒数第二个，以此类推
     */
    public static Object get(Object obj, int index) {
        //Assert.isTrue(obj.getClass().isArray(), "the param must be a Array, not a " + obj.getClass().getName());
        Class<?> compType = obj.getClass().getComponentType();
        if (compType.isPrimitive()) {
            if (compType == Boolean.TYPE) {
                boolean[] array = (boolean[]) obj;
                if (index < 0) return array[array.length + index];
                return array[index];
            } else if (compType == Byte.TYPE) {
                byte[] array = (byte[]) obj;
                if (index < 0) return array[array.length + index];
                return array[index];
            } else if (compType == Character.TYPE) {
                char[] array = (char[]) obj;
                if (index < 0) return array[array.length + index];
                return array[index];
            } else if (compType == Integer.TYPE) {
                int[] array = (int[]) obj;
                if (index < 0) return array[array.length + index];
                return array[index];
            } else if (compType == Long.TYPE) {
                long[] array = (long[]) obj;
                if (index < 0) return array[array.length + index];
                return array[index];
            } else if (compType == Float.TYPE) {
                float[] array = (float[]) obj;
                if (index < 0) return array[array.length + index];
                return array[index];
            } else if (compType == Double.TYPE) {
                double[] array = (double[]) obj;
                if (index < 0) return array[array.length + index];
                return array[index];
            } else if (compType == Short.TYPE) {
                short[] array = (short[]) obj;
                if (index < 0) return array[array.length + index];
                return array[index];
            } else {
                throw new IllegalArgumentException();
            }
        } else {
            Object[] array = (Object[]) obj;
            if (index < 0) return array[array.length + index];
            return array[index];
        }
    }

    /**
     * 操作未知类型的数组:set
     *
     * @param: index
     * 当序号为负数时，-1表示最后一个元素，-2表示倒数第二个，以此类推
     */
    public static void set(Object obj, int index, Object value) {
        //Assert.isTrue(obj.getClass().isArray(), "the param must be a Array, not a " + obj.getClass().getName());
        Class<?> compType = obj.getClass().getComponentType();
        if (compType.isPrimitive()) {
            if (compType == Boolean.TYPE) {
                boolean[] array = (boolean[]) obj;
                if (index < 0) index += array.length;
                array[index] = (Boolean) value;
            } else if (compType == Byte.TYPE) {
                byte[] array = (byte[]) obj;
                if (index < 0) index += array.length;
                array[index] = (Byte) value;
            } else if (compType == Character.TYPE) {
                char[] array = (char[]) obj;
                if (index < 0) index += array.length;
                array[index] = (Character) value;
            } else if (compType == Integer.TYPE) {
                int[] array = (int[]) obj;
                if (index < 0) index += array.length;
                array[index] = (Integer) value;
            } else if (compType == Long.TYPE) {
                long[] array = (long[]) obj;
                if (index < 0) index += array.length;
                array[index] = (Long) value;
            } else if (compType == Float.TYPE) {
                float[] array = (float[]) obj;
                if (index < 0) index += array.length;
                array[index] = (Float) value;
            } else if (compType == Double.TYPE) {
                double[] array = (double[]) obj;
                if (index < 0) index += array.length;
                array[index] = (Double) value;
            } else if (compType == Short.TYPE) {
                short[] array = (short[]) obj;
                if (index < 0) index += array.length;
                array[index] = (Short) value;
            } else {
                throw new IllegalArgumentException();
            }
        } else {
            Object[] array = (Object[]) obj;
            if (index < 0) index += array.length;
            array[index] = value;
        }
    }

    /**
     * 取交集
     *
     * @param ls
     * @param ls2
     * @return
     */
    public static Object[] intersect(Object[] ls, Object[] ls2) {
        HashSet<Object> set = new HashSet<Object>(Arrays.asList(ls));
        set.retainAll(Arrays.asList(ls2));
        return set.toArray();
    }

    /**
     * 取并集
     *
     * @param ls
     * @param ls2
     * @return
     */
    public static Object[] union(Object[] ls, Object[] ls2) {
        HashSet<Object> set = new HashSet<Object>(Arrays.asList(ls));
        for (Object o : ls2) {
            set.add(o);
        }
        return set.toArray();
    }

    /**
     * 取差集(即包含在ls，但不包含在ls2中的元素)
     * 可以立即为集合ls减去集合ls2
     *
     * @param ls
     * @param ls2
     * @return
     */
    public static Object[] minus(Object[] ls, Object[] ls2) {
        HashSet<Object> set = new HashSet<Object>(Arrays.asList(ls));
        set.removeAll(Arrays.asList(ls2));
        return set.toArray();
    }

    /**
     * 取两个集合，各自没有被对方包含的部分的集合。
     * 即从 并集中挖去交集。
     *
     * @param ls
     * @param ls2
     * @return
     */
    public static Object[] xor(Object[] ls, Object[] ls2) {
        //计算全集
        Set<Object> setAll = new HashSet<Object>(Arrays.asList(ls));
        for (Object o : ls2) {
            setAll.add(o);
        }
        //交集
        HashSet<Object> setInter = new HashSet<Object>(Arrays.asList(ls));
        setInter.retainAll(Arrays.asList(ls2));
        //取差
        setAll.removeAll(setInter);
        return setAll.toArray();
    }

    /**
     * Outputs an array as a String with {} surrounded or not, treating null as
     * an empty array.
     *
     * @param array
     * @param trimStartAndEnd 是否用{}包围结果
     * @return
     */
    public static String toString(Object array, boolean trimStartAndEnd) {
        if (trimStartAndEnd) {
            return new ToStringBuilder(array, ArraySimpleToStringStyle.ARRAY_SIMPLE_STYLE).append(
                    array).toString();
        }
        return org.apache.commons.lang.ArrayUtils.toString(array);
    }

}

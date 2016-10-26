package dbutil.common;

/**
 * Created by Kevin on 2016/10/25.
 */
public interface Groupable<A> {
    public A getGroupValue(String fieldName);
}
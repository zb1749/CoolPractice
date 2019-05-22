package setCompare.pojo;

import java.util.Comparator;

public class PersonComparator implements Comparator<ComparablePojo> {

    /**
     * comparator不用age排序，而用id排序，用来测试
     *
     * @param o1
     * @param o2
     * @return
     */
    @Override
    public int compare(ComparablePojo o1, ComparablePojo o2) {
        if (o1.getId() == o2.getId()) {
            return 0;
        } else if (o1.getId() > o2.getId()) {
            return 1;
        } else {
            return -1;
        }
    }
}

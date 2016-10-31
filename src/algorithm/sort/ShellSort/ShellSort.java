package algorithm.sort.ShellSort;

/**
 * 希尔排序基本步骤
 * 步骤一：选择适当步长(碰到偶数，则加1变成奇数，以减少相同下标的元素进行重复比较)，进行分组。
 * 步骤二：分另对各分分组进行插入排序。重复步骤一。
 * 步骤三：步长依次减半，当步长为1时，且已进行过插入排序后，数组已有序。
 * <p>
 * Created by Kevin on 2016/10/31.
 */
public class ShellSort {
    // 打印数组
    public static void PrintArray(int[] s) {
        System.out.printf("\n-----------------------------------");
        System.out.printf("\n下标:");
        for (int p = 0; p < s.length; p++) {
            System.out.printf("%3d,", p);
        }
        System.out.printf("\n值值:");
        for (Integer m : s) {
            System.out.printf("%3d,", m);
        }
        System.out.printf("\n-----------------------------------");
    }

    /**
     * 插入排序的变种
     *
     * @param start 开始下标
     * @param step  步长
     */
    private void insertSort(int start, int step, int[] s) {
        // 大循环
        int j = start + step;
        for (; j < s.length; j += step) {
            int i = j - step;
            int t = s[j];
            System.out.printf("\ntail_idx=%d, value=%d", j, t);
            // 小循环，注意，比较排序是从后向前比较的，找一个较大的值，这样实现算法更直观，更优美。
            for (; i >= start; i -= step) {
                System.out.printf("\ncompare, value=%d, idx=%d", t, i);
                if (t < s[i]) {
                    // 发现较小的数，向后移动
                    s[i + step] = s[i];
                    System.out.printf("\nmove, idx=%d to idx=%d", i, (i + step));
                } else {
                    // 比前面的第一个数大，则，不用处理
                    if (i == j - step) {
                        System.out.printf("\ndo not need compare others");
                    }
                    // 发现大于等于的数，t放到此位置
                    s[i + step] = t;
                    System.out.printf("\nmove, set value=%d on idx=%d", t, (i + step));
                    break;
                }
                PrintArray(s);
            }

            System.out.printf("\nmin, idx=%d, start_idx=%d", i, start);
            if (i < start) {
                System.out.printf("\nmin set, value=%d, idx=%d", t, (i + step));
                s[i + step] = t;
                PrintArray(s);
            }
        }
    }

    // shell sort
    public void shellSort(int[] s) {
        // 步长
        int step = s.length;

        while (true) {
            // 步长每次减半，遇到偶数加1变成奇数
            step = step / 2;
            if (step % 2 == 0) {
                step++;
            }

            // 每组步长的起始下标
            for (int start = 0; start < step; start++) {
                System.out.printf("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>步长=%d，起始下标=%d", step, start);
                // 对开始下标为j步长为i的分组排序
                this.insertSort(start, step, s);
            }

            // 当步长为1排过序后，数组已有序
            if (step == 1) {
                break;
            }
        }
    }


}
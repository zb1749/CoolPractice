package algorithm.sort.QuickSort;

/**
 * <pre>
 *  快速排序基本步骤
 *  步骤一：划分
 *      (1)选择数组的尾元素做为支点，支点选择方案有很多。
 *      (2)把>=支点的元素放到右边。
 *      (3)把<=支点的元素放到左边。
 *      (4)将支点放到正确的位置。
 *  步骤二：递归
 *      对支点左右两边的子数组再分别调用步骤一，直到子数组长度为1。
 * </pre>
 */
public class QuickSortAnother {

    // 快速排序
    public void quickSort(int[] s, int start, int end) {
        System.out.printf("\nquickSort,start=%d, end=%d", start, end);
        if (start >= end) {
            return;
        }

        int k = this.divide(s, start, end);
        System.out.printf("\n下标为:%d,值为:%d的元素，找到正确位置", k, s[k]);
        this.quickSort(s, start, k - 1);
        this.quickSort(s, k + 1, end);
    }

    // 在支点选择方案中，我是总是直接将s[end]选择为支点，当然你可以自己制定其它的方案
    // 举个例子，比较start, end, (start + end)/2这三个位置的值，选择中间值做为支点，并将其与end交换，最后选择end为支点
    private void selectPivot(int[] s, int start, int end) {
        // nothing to do
    }

    // 快速排序的划分过程
    private int divide(int[] s, int start, int end) {
        // 选择支点
        this.selectPivot(s, start, end);
        // 从左向右查找>=支点的元素的下标
        int i = start;
        // 从右向左查找<=支点的元素的下标
        int j = end - 1;
        // 空闲位置下标，初始总是end
        int k = end;
        // 临时存储支点
        int t = s[end];
        System.out.printf("\n开始划分,i=%d,j=%d,start=%d,end=%d,k=%d,s[end]=%d", i, j, start, end, k, t);

        while (true) {
            // 从左向右查找>=支点的元素
            while (i <= j && i != k) {
                if (s[i] >= t) {
                    System.out.printf("\n左向右找到，i=%d", i);
                    s[k] = s[i];
                    k = i;
                    break;
                }
                i++;
            }

            // 如果未找到，则交换，本次划分结束
            if (i > j) {
                System.out.printf("\n左向右未找到>=%d的元素", s[end]);
                s[i] = t;
                k = i;
                break;
            }

            // 从右向左查找<=支点的元素
            while (i <= j && j != k) {
                if (s[j] <= t) {
                    System.out.printf("\n右向左找到，j=%d", j);
                    s[k] = s[j];
                    k = j;
                    break;
                }
                j--;
            }

            // 从右向左没有找到，则交换，本次划分结束
            if (i >= j) {
                System.out.printf("\n右向左未找到<=%d的元素", s[end]);
                s[i] = t;
                k = i;
                break;
            }
        }

        // 打印数组
        PrintArray(s);

        return k;
    }

    // 打印数组
    public static void PrintArray(int[] s) {
        System.out.print("\n下标:");
        for (int p = 0; p < s.length; p++) {
            System.out.printf("%2d,", p);
        }
        System.out.print("\n值值:");
        for (Integer m : s) {
            System.out.printf("%2d,", m);
        }
    }

}

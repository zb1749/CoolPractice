package algorithm.sort.MergeSort;

/**
 * Created by Kevin on 2016/10/31.
 */
public class MergeSort {

    public void mergeSort(int[] s, int start, int end) {
        if (start >= end) {
            return;
        }

        int m = (start + end) / 2;
        int start1 = start;
        int end1 = m;
        int start2 = m + 1;
        int end2 = end;
        this.mergeSort(s, start1, end1);
        this.mergeSort(s, start2, end2);

        this.merge(s, start1, end1, start2, end2);
    }

    // 合并两个有序数组为一个有序数组
    private void merge(int[] s, int start1, int end1, int start2, int end2) {
        System.out.printf("\n开始合并,合并范围,[%d,%d],[%d,%d]", start1, end1, start2, end2);
        int start = start1;
        // 临时数组
        int[] t = new int[end2 - start1 + 1];
        // 临时数组下标
        int i = 0;

        // 比较开始
        while (start1 <= end1 && start2 <= end2) {
            if (s[start1] < s[start2]) {
                t[i] = s[start1];
                start1++;
                i++;
                continue;
            }
            if (s[start1] > s[start2]) {
                t[i] = s[start2];
                start2++;
                i++;
                continue;
            }
        }

        // 拷贝剩余元素
        while (start1 <= end1) {
            t[i] = s[start1];
            start1++;
            i++;
        }

        // 拷贝剩余元素
        while (start2 <= end2) {
            t[i] = s[start2];
            start2++;
            i++;
        }

        // 将已排好序的元素放回对应位置
        for (i = 0; i < t.length; i++) {
            s[start + i] = t[i];
        }

        // 打印数组
        PrintArray(s);
    }

    // 打印数组
    private static void PrintArray(int[] s) {
        System.out.print("\n下标:");
        for (int p = 0; p < s.length; p++) {
            System.out.printf("%2d,", p);
        }
        System.out.print("\n值值:");
        for (Integer m : s) {
            System.out.printf("%2d,", m);
        }
        System.out.print("\n-----------------------------");
    }

    public static void main(String[] args) {
        // int[] s = new int[] { 99, 88, 5, 99, 7, 9, 3, 8, 10, 90, 56, 83, 39, 22 };
        int[] s = new int[] { 9, 2, 3, 7, 6, 5, 8, };

        // 打印数组
        PrintArray(s);

        MergeSort mergeSort = new MergeSort();
        mergeSort.mergeSort(s, 0, s.length - 1);

        // 打印数组
        PrintArray(s);
    }

}

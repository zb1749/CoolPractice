package algorithm.sort.QuickSort;

/**
 * Created by Kevin on 2016/10/31.
 */
public class QuickSortAnotherTest {

    public static void main(String[] args) {


        //QuickSortAnother Test
        int[] s = new int[] { 99, 88, 5, 99, 7, 9, 3, 8, 10, 90, 56, 83, 39, 22 };
        // int[] s = new int[] { 99, 88, 5, 99, 7, 9, 3, 8,};
        // 打印数组
        QuickSortAnother.PrintArray(s);

        QuickSortAnother quickSortAnother = new QuickSortAnother();
        quickSortAnother.quickSort(s, 0, s.length - 1);

        // 打印数组
        QuickSortAnother.PrintArray(s);
    }
}

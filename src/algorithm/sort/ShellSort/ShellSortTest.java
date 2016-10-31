package algorithm.sort.ShellSort;

/**
 * Created by Kevin on 2016/10/31.
 */
public class ShellSortTest {
    public static void main(String[] args) {
        int[] s = new int[]{30, 25, 16, 7, 19, 20, 7, 5, 2, 8, 3, 1, 6, 9, 50};
        ShellSort.PrintArray(s);

        ShellSort shellSort = new ShellSort();
        shellSort.shellSort(s);

        ShellSort.PrintArray(s);
    }
}

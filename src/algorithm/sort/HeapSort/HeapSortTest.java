package algorithm.sort.HeapSort;

/**
 * Created by Kevin on 2016/10/31.
 */
public class HeapSortTest {

    public static void main(String[] args) {
        // int[] s = new int[] { 99, 88, 5, 99, 7, 9, 3, 8, 10, 90, 56, 83, 39, 22 };
        int[] s = new int[]{7, 5, 2, 8, 3, 1, 6, 9};
        HeapSort.PrintArray(s);

        HeapSort heapSort = new HeapSort();
        heapSort.initHeap(s);
        HeapSort.PrintArray(s);
        heapSort.heapSort(s);

        HeapSort.PrintArray(s);
    }
}

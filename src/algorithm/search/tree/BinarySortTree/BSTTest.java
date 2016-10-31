package algorithm.search.tree.BinarySortTree;

/**
 * Created by Kevin on 2016/10/31.
 */
public class BSTTest {
    public static void main(String[] args) {
        // 初始化二叉查找树
        BST bst = new BST();
        bst.insert(50);
        bst.insert(30);
        bst.insert(80);
        bst.insert(10);
        bst.insert(40);
        bst.insert(35);
        bst.insert(90);
        bst.insert(85);
        bst.insert(5);
        bst.insert(15);
        bst.insert(20);
        bst.insert(13);
        bst.insert(3);
        bst.insert(8);
        bst.insert(37);
        bst.insert(70);
        bst.insert(60);
        bst.insert(75);
        bst.insert(78);
        bst.insert(72);
        bst.insert(95);
        bst.insert(99);

        bst.bfsLevel();
        bst.bfsTree();

        // int v = 35;
        // Node node = bst.search(v);

        // System.out.print("\ndepth:" + bst.getDepth(node));

        // int k = bst.getMaxLevel(node);
        // System.out.print("\n树的深度,R:" + v + ",V:" + k);
        //
        // int k2 = bst.getTotalNode(node);
        // System.out.print("\n树总结点数,R:" + v + ",V:" + k2);
        //
        // Node nodeMax = bst.getMaxNode(node);
        // System.out.print("\n树中最大值,R:" + v + ",V:" + (nodeMax == null ? null : nodeMax.value));

        //int value = 30;
        //bst.delete(value);
        //bst.bfsTree();
    }
}

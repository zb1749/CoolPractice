package algorithm.question.TreeLevelCount;

/**
 * 关于求树中每层的节点总数
 * <p>
 * 我们用链表来表示树，树也是一种简单的图，同样也可以用二维矩阵来表示。
 * <p>
 * 方案1：基于树的深度优先遍历，进行递归，推荐。
 * <p>
 * 方案2：基于树的深度优先遍历，进行迭代。
 * <p>
 * 方案3：基于树的广度优先遍历，进行迭代。
 * <p>
 * Created by Kevin on 2016/10/31.
 */
public class TreeLevelCountTest {
    public static void main(String[] args) {
        TreeLevelCount tlc = new TreeLevelCount();
        tlc.initTree();

        System.out.printf("------------------- 深度优先遍历，递归求解 -------------------\n");
        tlc.levelMap.clear();
        tlc.dfsRecursiveLevelCount(tlc.root, 1);
        tlc.printLevelCount();

        System.out.printf("------------------- 深度优先遍历，迭代求解 -------------------\n");
        tlc.levelMap.clear();
        tlc.dfsStackLevelCount();
        tlc.printLevelCount();

        System.out.printf("------------------- 广度优先遍历 -------------------\n");
        tlc.levelMap.clear();
        tlc.bfsLevelCount();
        tlc.printLevelCount();
    }
}

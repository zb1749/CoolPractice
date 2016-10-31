package algorithm.question.TreeLevelCount;

import java.util.*;

/**
 * <pre>
 * 示例树如下
 * ***********************************************
 *                      a
 *          b           c           d
 *      e           f       g       h
 *                              i       j
 *  ***********************************************
 * </pre>
 */
public class TreeLevelCount {
    // 树的根节点
    protected TreeLevelNode root;
    // 每层的节点总数
    protected Map<Integer, Integer> levelMap = new HashMap<Integer, Integer>();

    protected void initTree() {
        // init all nodes
        TreeLevelNode a = new TreeLevelNode("a");
        TreeLevelNode b = new TreeLevelNode("b");
        TreeLevelNode c = new TreeLevelNode("c");
        TreeLevelNode d = new TreeLevelNode("d");
        TreeLevelNode e = new TreeLevelNode("e");
        TreeLevelNode f = new TreeLevelNode("f");
        TreeLevelNode g = new TreeLevelNode("g");
        TreeLevelNode h = new TreeLevelNode("h");
        TreeLevelNode i = new TreeLevelNode("i");
        TreeLevelNode j = new TreeLevelNode("j");

        // set root
        this.root = a;

        // set subNodes
        a.addSub(new TreeLevelNode[]{b, c, d});

        b.addSub(new TreeLevelNode[]{e});
        c.addSub(new TreeLevelNode[]{f, g});
        d.addSub(new TreeLevelNode[]{h});

        h.addSub(new TreeLevelNode[]{i, j});
    }

    // 打印结果
    protected void printLevelCount() {
        System.out.printf("------------------- level count\n");
        for (Map.Entry<Integer, Integer> entry : this.levelMap.entrySet()) {
            System.out.printf("level:%d, count:%d\n", entry.getKey(), entry.getValue());
        }
    }

    // 深度优先遍历，递归方式，统计每层的节点数
    // 简单，高效
    protected void dfsRecursiveLevelCount(TreeLevelNode node, int level) {
        System.out.printf("visit, node:%s, level:%d\n", node.getValue(), level);

        // 更新计数
        Integer levelCount = this.levelMap.get(level);
        if (levelCount == null) {
            levelCount = 1;
        } else {
            levelCount++;
        }
        this.levelMap.put(level, levelCount);

        // 对每个子节点进行递归调用
        for (TreeLevelNode subNode : node.getSubNodeList()) {
            dfsRecursiveLevelCount(subNode, level + 1);
        }
    }

    // 深度优先遍历，借用栈(非递归)，统计每层的节点数
    // 稍微有点麻烦
    protected void dfsStackLevelCount() {
        // 方案1，存储已访问过的节点
        // 方案2，也可以存储某节点已访问过的子节点数，这样可以直接从子节点列表中取出下一个未访问的子节点，这种情况下，用ArrayList存储子节点更高效
        // 方案3，TreeLevelNode中维护一个指针，直接指向同辈中的下一个节点
        Set<String> nodeValueSet = new HashSet<String>();
        // 用来保存当前路径的栈
        Stack<TreeLevelNode> stack = new Stack<TreeLevelNode>();
        // 初始加入root节点
        stack.add(this.root);

        // 根节点的level永远是1，levelCount永远是1
        this.levelMap.put(1, 1);
        // 将root节点加入已访问
        nodeValueSet.add(this.root.getValue());
        System.out.printf("visit, node:%s, level:%d\n", this.root.getValue(), 1);

        out:
        while (!stack.isEmpty()) {
            // 访问栈顶元素，但不弹出
            TreeLevelNode topNode = stack.peek();

            // 当其没有子节点时，弹出栈顶元素
            if (topNode.getSubNodeList().size() == 0) {
                stack.pop();
                continue;
            }

            // 寻找一个该节点的未访问的子节点
            for (TreeLevelNode nod : topNode.getSubNodeList()) {
                if (!nodeValueSet.contains(nod.getValue())) {
                    // 如果子节点没有被访问过，则加入栈顶
                    stack.push(nod);
                    // 将该节点已访问
                    nodeValueSet.add(nod.getValue());
                    System.out.printf("visit, node:%s, level:%d\n", nod.getValue(), stack.size());

                    // 注意，栈的当前大小，就是栈顶元素在树中的层次
                    // 更新level的统计总数
                    Integer count = this.levelMap.get(stack.size());
                    if (count == null) {
                        count = 1;
                    } else {
                        count++;
                    }
                    this.levelMap.put(stack.size(), count);

                    // 断续深度遍历，相当于调用了递归方法
                    continue out;
                }
            }

            // 如果该节点的所有孩子节点都访问过，则弹出栈顶元素
            stack.pop();
        }
    }

    // 广度优先遍历，借用队列，统计每层的节点数
    // 稍微有点麻烦
    protected void bfsLevelCount() {
        // 存储元素的层级数
        Map<String, Integer> nodeLevelMap = new HashMap<String, Integer>();
        // 队列
        Queue<TreeLevelNode> queue = new LinkedList<TreeLevelNode>();

        // 初始加入root节点
        queue.offer(this.root);
        // 根节点的level永远是1，levelCount永远是1
        this.levelMap.put(1, 1);
        nodeLevelMap.put(this.root.getValue(), 1);
        System.out.printf("visit, node:%s, level:%d\n", this.root.getValue(), 1);

        while (!queue.isEmpty()) {
            // 访问队列头，并删除
            TreeLevelNode head = queue.poll();

            // 将子节点依次加入到队列尾
            for (TreeLevelNode nd : head.getSubNodeList()) {
                queue.offer(nd);

                // 子节点所在的层次为：父节点层级加1
                int levelCurrent = nodeLevelMap.get(head.getValue()) + 1;
                // 记录节点的层级
                nodeLevelMap.put(nd.getValue(), levelCurrent);
                System.out.printf("visit, node:%s, level:%d\n", nd.getValue(), levelCurrent);

                // 更新level的统计总数
                Integer count = this.levelMap.get(levelCurrent);
                if (count == null) {
                    count = 1;
                } else {
                    count++;
                }
                this.levelMap.put(levelCurrent, count);
            }
        }
    }

}

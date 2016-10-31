package algorithm.question.OneOfMinEdge;

import java.util.Stack;

/**
 * 基于广度优先算法实现
 *
 * Created by Kevin on 2016/10/31.
 */
public class OneOfMinEdgeTest {
    public static void main(String[] args) {
        String[] nodes = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I"};
        GrfOneOfMinEdge grf = new GrfOneOfMinEdge(9, nodes);
        grf.initGrf();
        grf.printMatrix();

        System.out.println("------ 寻找路径开始 ------");
        grf.resetVisited();

        // ------------------ 计算路径
        int origin = 0;
        int goal = 8;
        grf.bfsQueueOneofMinEdge(origin, goal);

        // ------------------ 输出计算结果
        System.out.print("\n图的所有节点                 :");
        for (String node : grf.nodes) {
            System.out.print(node + ",");
        }
        System.out.print("\n起点到各节点的最少边数:");
        for (int i : grf.dis) {
            System.out.print(i + ",");
        }
        System.out.print("\n前驱节点                        :");
        for (int i : grf.preIndex) {
            if (i != -1) {
                System.out.print(grf.nodes[i] + ",");
            } else {
                System.out.print("-" + ",");
            }
        }
        System.out.print("\n起点A到终点I所结过的含有最少边数的路径:");
        Stack<String> stack = new Stack<String>();
        int pre = grf.preIndex[goal];
        stack.push(grf.nodes[goal]);
        while (pre != -1) {
            stack.push(grf.nodes[pre]);
            pre = grf.preIndex[pre];
        }
        while (!stack.isEmpty()) {
            System.out.print(stack.pop());
        }
        System.out.println();
        System.out.println("------ 寻找路径结束 ------");
    }
}

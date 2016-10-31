package algorithm.search.graph.DfsBfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by Kevin on 2016/10/31.
 */
public class DfsBfsTest {
    public static void main(String[] args) {
        String[] nodes = new String[] { "A", "B", "C", "D", "E", "F", "G", "H", "I" };
        GrfDfsBfs grf = new GrfDfsBfs(9, nodes);
        grf.initGrf();
        grf.printMatrix();

        System.out.println("------ 深度优先遍历(递归)开始 ------");
        grf.resetVisited();
        grf.dfsRecursive(0);
        System.out.println();
        System.out.println("------ 深度优先遍历(递归)结束 ------");

        System.out.println("------ 深度优先遍历(栈)开始 ------");
        grf.resetVisited();
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(0);
        grf.matirx[0][0] = 1;
        System.out.print(grf.nodes[0]);
        grf.dfsStack(stack);
        System.out.println();
        System.out.println("------ 深度优先遍历(栈)结束 ------");

        System.out.println("------ 广度优先遍历开始 ------");
        grf.resetVisited();
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(0);
        grf.bfsQueue(queue);
        System.out.println();
        System.out.println("------ 广度优先遍历结束 ------");
    }
}

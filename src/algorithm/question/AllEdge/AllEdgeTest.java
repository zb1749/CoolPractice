package algorithm.question.AllEdge;

import java.util.Stack;

/**
 * 求无向无权图起点到终点的所有路径
 * <p>
 * <p>
 * 基本思路：
 * <p>
 * 基于图的深度优先遍历进行修改。
 * <p>
 * 0：初始栈中只有起点元素。
 * <p>
 * 步骤1：如果栈为空，则返回，否则，访问栈顶节点，但先不删除栈顶元素。
 * <p>
 * 步骤2：如果该元素的邻接点
 * <p>
 * (1)是终点，则打印路径。
 * <p>
 * (2)在栈中已存在，则是环路，不处理。
 * <p>
 * (2)正常，则将该邻接点入栈，继续步骤1
 * <p>
 * Created by Kevin on 2016/10/31.
 */
public class AllEdgeTest {
    public static void main(String[] args) {
        String[] nodes = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I"};
        GrfAllEdge grf = new GrfAllEdge(9, nodes);
        grf.initGrf();
        grf.printMatrix();

        System.out.print("\n------ 寻找起点到终点的所有路径开始 ------");
        grf.resetVisited();
        int origin = 0;
        int goal = 8;
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(origin);
        grf.dfsStack(-1, goal, stack);
        System.out.print("\n------ 寻找起点到终点的所有路径结束 ------");
    }
}

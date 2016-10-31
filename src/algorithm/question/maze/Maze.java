package algorithm.question.maze;

import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

/**
 * 迷宫求解(二维矩阵)
 */
public class Maze {
    // 迷宫的二维矩阵存储
    private int[][] matirx;
    // 迷宫起点
    private Point start;
    // 迷宫终点
    private Point end;

    public Maze(int i, int j) {
        this.matirx = new int[i][j];
        System.out.printf("\nmatirx, i=%d, j=%d", i, j);
    }

    // 打印普通迷宫
    protected void printMatrix() {
        System.out.printf("\n----------------- matrix -----------------");
        System.out.printf("\n%4s", "");
        for (int i = 0; i < this.matirx.length; i++) {
            System.out.printf("%2d", i);
        }
        System.out.printf("\n----");
        for (int i = 0; i < this.matirx.length; i++) {
            System.out.printf("%s", "--");
        }
        System.out.printf("\n");
        for (int i = 0; i < this.matirx.length; i++) {
            System.out.printf("%2s%2s", i, "|");
            for (int j = 0; j < this.matirx[0].length; j++) {
                System.out.printf("%2d", this.matirx[i][j]);
            }
            System.out.print("\n");
        }
        System.out.println("----------------- matrix -----------------");
    }

    // 迷宫手动初始化
    protected void initMaze2() {
        this.start = new Point(0, 3);
        this.end = new Point(8, 6);

        this.matirx = new int[9][9];
        // ------------------------{0, 1, 2, 3, 4, 5, 6, 7, 8}
        this.matirx[0] = new int[]{1, 1, 1, 0, 1, 1, 1, 1, 1};
        this.matirx[1] = new int[]{1, 1, 1, 0, 0, 0, 0, 0, 1};
        this.matirx[2] = new int[]{1, 1, 1, 0, 1, 1, 0, 1, 1};
        this.matirx[3] = new int[]{1, 0, 0, 0, 0, 0, 1, 1, 1};
        this.matirx[4] = new int[]{1, 0, 1, 1, 1, 0, 1, 1, 1};
        this.matirx[5] = new int[]{1, 0, 0, 0, 0, 0, 1, 1, 1};
        this.matirx[6] = new int[]{1, 1, 0, 1, 0, 0, 0, 1, 1};
        this.matirx[7] = new int[]{1, 1, 0, 0, 0, 1, 0, 1, 1};
        this.matirx[8] = new int[]{1, 1, 1, 1, 1, 1, 0, 1, 1};
    }

    // 迷宫自动初始化
    private void initMaze() {
        int i = this.matirx.length;
        int j = this.matirx[0].length;

        this.matirx = new int[i][j];

        // 初始全部设置为墙
        for (int m = 0; m < i; m++) {
            for (int n = 0; n < j; n++) {
                this.matirx[m][n] = 1;
            }
        }

        // 随机设置通行点
        Random rd = new Random();
        int ii = -1;
        int jj = -1;
        for (int k = 0; k < (i * j) / 2; k++) {
            ii = rd.nextInt(i);
            jj = rd.nextInt(j);

            // 不设置矩阵的四条边
            if (ii == 0 || ii == (this.matirx.length - 1) || jj == 0 || jj == (this.matirx[0].length - 1)) {
                continue;
            }

            this.matirx[ii][jj] = 0;
        }

        // 将起点与终点设置为通行点
        this.matirx[this.start.i][this.start.j] = 0;
        this.matirx[this.end.i][this.end.j] = 0;
    }

    // ---------------------------------- 输入迷宫的起点与终点开始 ----------------------------------
    // 控制台输入迷宫起点
    private void startPoint() throws Exception {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            int i = 0;
            System.out.printf("\nstart.j:");
            int j = scanner.nextInt();

            this.start = new Point(i, j);

            if (!this.checkPoint(this.start)) {
                System.out.printf("\nnot a valid number, enter again.");
                continue;
            }

            this.matirx[this.start.i][this.start.j] = 0;

            System.out.printf("\nStart Point, i=%d, j=%d", this.start.i, this.start.j);
            break;
        }
    }

    // 控制台输入迷宫终点
    private void endPoint() throws Exception {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            int i = this.matirx.length - 1;
            System.out.printf("\nend.j:");
            int j = scanner.nextInt();

            this.end = new Point(i, j);

            if (!this.checkPoint(this.end)) {
                System.out.printf("\nnot a valid number, enter again.");
                continue;
            }

            this.matirx[this.end.i][this.end.j] = 0;

            System.out.printf("\nEnd Point, i=%d, j=%d", this.end.i, this.end.j);
            break;
        }
    }

    // 检查输入的起点与终点是否合法
    private boolean checkPoint(Point point) {
        boolean flag = true;

        if (point.i >= this.matirx.length) {
            flag = false;
        }

        if (point.j >= this.matirx[0].length) {
            flag = false;
        }

        return flag;
    }

    // ---------------------------------- 输入迷宫的起点与终点结束 ----------------------------------

    /**
     * <pre>
     *  寻找迷宫所有可行路径，基于深度遍历
     *  步骤1：将起点入栈
     *  步骤2：取出当前栈顶元素，但不弹出
     *  步骤3：按顺时针方向，分别访问栈顶元素的正上，正右，正下，正左四个邻接点
     *  步骤4：如果不是死胡同，且
     *          如果是墙，则返回；
     *          如果已在当前路径中，则返回；
     *          如果超出边界，则返回；
     *          如果是终点，则打印路径；
     *          否则，将这个邻接点压入栈顶，继续步骤2
     *  步骤5：如果是死胡同，则弹出栈顶元素，继续步骤2
     * </pre>
     *
     * @throws Exception
     */
    protected void search() throws Exception {
        // 用户存储路径的当前栈
        Stack<Point> stack = new Stack<Point>();
        // 将起点压入栈顶
        stack.push(this.start);
        // 寻找路径，直到栈为空
        out:
        while (!stack.isEmpty()) {
            // 访问当前栈顶元素，但不弹出
            Point top = stack.peek();
            System.out.printf("\ntop,[%2d,%2d],", top.i, top.j);

            // 是否是死胡同
            while (!top.deadEnd()) {
                // 按上右下左，顺时针方向，依次访问栈顶元素的邻接点
                Point next = top.getNeighBour();
                // 起点的正上为null
                if (next == null) {
                    continue;
                }

                System.out.printf("\nnext,[%2d,%2d],", next.i, next.j);

                // 探测的下一个元素不能是当前路径中的元素(即产生回路时直接探测下一个邻接点)
                if (stack.contains(next)) {
                    System.out.printf("\nstack contains,[%2d,%2d],", next.i, next.j);
                    continue;
                }

                // 遇到墙时，直接探测下一个邻接点
                if (this.matirx[next.i][next.j] == 1) {
                    System.out.printf("\nwall,[%2d,%2d],", next.i, next.j);
                    continue;
                }

                // 遇到终点时，打印当前路径
                if (next.equals(this.end)) {
                    stack.push(next);
                    printPath(stack);
                    stack.pop();
                    continue;
                }

                System.out.printf("\npush,[%2d,%2d],", next.i, next.j);
                stack.push(next);
                continue out;
            }

            // 某元素的，正上，正右，正下，正左，四个邻接元素都探测过，没有出路，弹出栈顶元素
            stack.pop();
            System.out.printf("\npop,[%2d,%2d],", top.i, top.j);
        }

        System.out.printf("\nterminal...");
    }

    // 打印路径
    private void printPath(Stack<Point> stack) {
        System.out.printf("\n...................... find path start ......................");
        System.out.printf("\n");
        // for (Point p : stack) {
        // System.out.printf("[%2d,%2d],", p.i, p.j);
        // this.matirx[p.i][p.j] = 9;
        // }
        for (int i = 0; i < this.matirx.length; i++) {
            System.out.printf("\n");
            for (int j = 0; j < this.matirx[0].length; j++) {
                if (stack.contains(new Point(i, j))) {
                    System.out.printf("%2s", "#");
                } else {
                    System.out.printf("%2s", this.matirx[i][j]);
                }
            }
        }
        System.out.printf("\n...................... find path enddd ......................");
    }



}

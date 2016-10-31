package algorithm.question.OneOfMinEdge;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 无权无向图<br/>
 * 寻找这样的路径：从起点到终点所经过的边最少<br/>
 * 基于广度优先算法实现
 */
public class GrfOneOfMinEdge {
    // 图的顶点总数
    private int total;
    // 各顶点基本信息
    protected String[] nodes;
    // 图的邻接矩阵
    private int[][] matirx;
    // 各顶点的前驱下标
    protected int[] preIndex;
    // 起点到各顶点所经过的边的总数(最少)
    protected int[] dis;

    public GrfOneOfMinEdge(int total, String[] nodes) {
        this.total = total;
        this.nodes = nodes;
        this.matirx = new int[total][total];
        this.preIndex = new int[total];
        this.dis = new int[total];
    }

    // 图的广度优先遍历
    protected void bfsQueueOneofMinEdge(int origin, int goal) {
        Queue<Integer> queue = new LinkedList<Integer>();

        // 将起点加入队列
        queue.add(origin);
        // 起点已标记
        this.matirx[origin][origin] = 1;
        // 起点的前驱下标为-1
        this.preIndex[origin] = -1;
        // 起点到自己的距离为0
        this.dis[origin] = 0;

        // 设置一个双层循环跳出标记
        out: while (!queue.isEmpty()) {
            System.out.print("\n当前队列中的元素:");
            for (Integer j : queue) {
                System.out.print(j);
            }
            System.out.print("\n");

            int k = queue.poll().intValue();
            for (int i = 0; i < this.total; i++) {
                // 未标记，不在左占到右下中心线上，的邻接点
                if (this.matirx[k][i] == 1 && this.matirx[i][i] == 0 && k != i) {
                    // 已标记
                    this.matirx[i][i] = 1;
                    // 该顶点的前驱为k
                    this.preIndex[i] = k;
                    // 更新所经过的边数
                    this.dis[i] = this.dis[k] + 1;
                    // 将该顶点加入队列
                    queue.offer(i);

                    System.out.println("find:" + this.nodes[i] + ", dis:" + this.dis[i] + ", pre:" + this.nodes[k]);

                    if (i == goal) {
                        break out;
                    }
                }
            }
        }
    }

    protected void printMatrix() {
        System.out.println("----------------- matrix -----------------");
        System.out.println("---0-1-2-3-4-5-6-7-8--");
        System.out.println("---A-B-C-D-E-F-G-H-I--");
        for (int i = 0; i < this.total; i++) {
            System.out.print(" " + this.nodes[i] + "|");
            for (int j = 0; j < this.total; j++) {
                System.out.print(this.matirx[i][j] + "-");
            }
            System.out.print("\n");
        }
        System.out.println("----------------- matrix -----------------");
    }

    // 设置[i][i]位置处的元素值为0，0表示图中的定点i未被访问，1表示图中的定点i已被访问
    protected void resetVisited() {
        for (int i = 0; i < this.total; i++) {
            this.matirx[i][i] = 0;
        }
    }

    // 初始化图数据
    // 0---1---2---3---4---5---6---7---8---
    // A---B---C---D---E---F---G---H---I---
    protected void initGrf() {
        // A-B, A-D, A-E
        this.matirx[0][1] = 1;
        this.matirx[1][0] = 1;
        this.matirx[0][3] = 1;
        this.matirx[3][0] = 1;
        this.matirx[0][4] = 1;
        this.matirx[4][0] = 1;
        // B-C
        this.matirx[1][2] = 1;
        this.matirx[2][1] = 1;
        // C-F
        this.matirx[2][5] = 1;
        this.matirx[5][2] = 1;
        // D-E, D-G
        this.matirx[3][4] = 1;
        this.matirx[4][3] = 1;
        this.matirx[3][6] = 1;
        this.matirx[6][3] = 1;
        // E-F, E-H
        this.matirx[4][5] = 1;
        this.matirx[5][4] = 1;
        this.matirx[4][7] = 1;
        this.matirx[7][4] = 1;
        // F-H, F-I
        this.matirx[5][7] = 1;
        this.matirx[7][5] = 1;
        this.matirx[5][8] = 1;
        this.matirx[8][5] = 1;
        // G-H
        this.matirx[6][7] = 1;
        this.matirx[7][6] = 1;
        // H-I
        this.matirx[7][8] = 1;
        this.matirx[8][7] = 1;
    }

    // 初始化图数据
    // 0---1---2---3---4---5---6---7---8---
    // A---B---C---D---E---F---G---H---I---
    private void initGrf2() {
        // A-B, A-D, A-E
        this.matirx[0][1] = 1;
        this.matirx[1][0] = 1;
        this.matirx[0][3] = 1;
        this.matirx[3][0] = 1;
        this.matirx[0][4] = 1;
        this.matirx[4][0] = 1;
        // B-C
        this.matirx[1][2] = 1;
        this.matirx[2][1] = 1;
        // C-F
        this.matirx[2][5] = 1;
        this.matirx[5][2] = 1;
        // D-E
        this.matirx[3][4] = 1;
        this.matirx[4][3] = 1;
        // E-F, E-H
        this.matirx[4][5] = 1;
        this.matirx[5][4] = 1;
        this.matirx[4][7] = 1;
        this.matirx[7][4] = 1;
        // F-H, F-I
        this.matirx[5][7] = 1;
        this.matirx[7][5] = 1;
        this.matirx[5][8] = 1;
        this.matirx[8][5] = 1;
        // G-H
        this.matirx[6][7] = 1;
        this.matirx[7][6] = 1;
        // H-I
        this.matirx[7][8] = 1;
        this.matirx[8][7] = 1;
    }

    // 初始化图数据
    // 0---1---2---3---4---5---6---7---8---
    // A---B---C---D---E---F---G---H---I---
    private void initGrf3() {
        // A-D, A-E
        this.matirx[0][3] = 1;
        this.matirx[3][0] = 1;
        this.matirx[0][4] = 1;
        this.matirx[4][0] = 1;
        // B-C
        this.matirx[1][2] = 1;
        this.matirx[2][1] = 1;
        // C-F
        this.matirx[2][5] = 1;
        this.matirx[5][2] = 1;
        // E-H, E-I
        this.matirx[4][7] = 1;
        this.matirx[7][4] = 1;
        this.matirx[4][8] = 1;
        this.matirx[8][4] = 1;
        // F-I
        this.matirx[5][8] = 1;
        this.matirx[8][5] = 1;
        // G-H
        this.matirx[6][7] = 1;
        this.matirx[7][6] = 1;
    }



}

package algorithm.search.graph.dijkstra;

/**
 * Created by Kevin on 2016/10/31.
 */
public class DijkstraTest {
    public static void main(String[] args) {
        String[] nodes = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I"};
        GrfDijkstra grf = new GrfDijkstra(nodes.length, nodes);
        grf.initGrf();
        grf.printMatrix();

        System.out.println();
        System.out.println("------ Dijkstra算法-(迪杰斯特拉)算法之迭代开始 ------");
        grf.dijkstra(0);
        grf.printDis(0, "屌", "最终值");
        System.out.print("\n");
        System.out.println("------ Dijkstra算法-(迪杰斯特拉)算法之迭代结束 ------");

        System.out.println();
        System.out.println("------ Dijkstra算法-(迪杰斯特拉)算法之优先队列开始 ------");
        grf.dijkstraPQ();
        grf.printDis(0, "屌", "最终值");
        System.out.print("\n");
        System.out.println("------ Dijkstra算法-(迪杰斯特拉)算法之优先队列结束 ------");
    }
}

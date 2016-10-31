package algorithm.question.maze;

/**
 * Created by Kevin on 2016/10/31.
 */
public class MazeTest {
    public static void main(String[] args) throws Exception {
        Maze maze = new Maze(9, 9);
        // maze.enterStartPoint();
        // maze.enterEndPoint();
        // maze.initMaze();
        maze.initMaze2();
        maze.printMatrix();
        maze.search();
    }
}

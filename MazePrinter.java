import java.util.LinkedList;

public class MazePrinter {
    private MazeFiller filler;
    private int[][] maze;
    private MazeSolver solver;
    private LinkedList<Point> solvedMaze;

    public MazePrinter(MazeFiller filler, int[][] maze) {
        this.filler = filler;
        this.maze = maze;
    }

    public MazePrinter(MazeSolver solver) {
        this.solver = solver;
    }

    public void printMaze() {
        if (filler.getAnswer() == 'n')
            return;
        for (int i = 0; i < filler.getRowNumber(); i++) {
            for (int j = 0; j < filler.getColumnNumber(); j++)
                System.out.print(maze[i][j] + " ");
            System.out.println();
        }
    }

    public void printResults() {
        solvedMaze = solver.solveMaze();
        if (solvedMaze == null) return;
        System.out.println("Results:");
        for (int coordinate = 0; coordinate < solvedMaze.size(); coordinate++)
            System.out.print(solvedMaze.get(coordinate).convertCoordinate() + " ");
    }
}
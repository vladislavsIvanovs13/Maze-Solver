import java.util.LinkedList;
import java.util.Collections;

public class MazeSolver {
    private static LinkedList<Point> path = new LinkedList<Point>();
    private MazeFiller filler;
    private int[][] maze;
    private byte methodNumber;

    public MazeSolver(int[][] maze) {
        this.maze = maze;
    }

    public MazeSolver(MazeFiller filler, byte methodNumber, int[][] maze) {
        this.filler = filler;
        this.methodNumber = methodNumber;
        this.maze = maze;
    }

    public LinkedList<Point> solveMaze() {
        switch(methodNumber) {
            case 1:
                path = findPath(maze, filler.getRowNumber(), filler.getColumnNumber()); break;
            case 2:
                path = findAltPath(maze, filler.getRowNumber(), filler.getColumnNumber()); break;
        }
        return path;
    }
    
    public LinkedList<Point> findPath(int[][] maze, int rowNumber, int columnNumber) {
        if (solve(maze, 0, 0, path, rowNumber, columnNumber) == false) {
            System.out.println("Solution doesn't exist");
            return null;
        }
        return path;
    }

    public boolean solve(int[][] maze, int x, int y, LinkedList<Point> path, int rowNumber, int columnNumber) {
        if ((x == rowNumber - 1) && (y == columnNumber - 1) && (maze[x][y] == 0)) {
            Point coordinate = new Point(x, y);
            path.add(coordinate);
            return true;
        }

        if ((x >= 0) && (x < rowNumber) && (y >= 0) && (y < columnNumber) && (maze[x][y] == 0)) {
            for (var coordinate : path)
                if ((coordinate.getX() == x) && (coordinate.getY() == y))
                    return false;

            path.add(new Point(x, y));

            if (solve(maze, x + 1, y, path, rowNumber, columnNumber))
                return true;

            if (solve(maze, x, y + 1, path, rowNumber, columnNumber))
                return true;

            if (solve(maze, x - 1, y, path, rowNumber, columnNumber))
                return true;

            if (solve(maze, x, y - 1, path, rowNumber, columnNumber))
                return true;

            for (var coordinate : path)
                if ((coordinate.getX() == x) && (coordinate.getY() == y))
                    path.remove(coordinate);
                
            return false;
        }

        return false;
    }

    public LinkedList<Point> findAltPath(int[][] maze, int rowNumber, int columnNumber) {
        var coordinate = solveAlt(maze, path, rowNumber, columnNumber);
        
        if (coordinate == null) {
            System.out.println("Solution doesn't exist");
            return null;
        }
        
        while (coordinate.getParent() != null) {
            path.add(coordinate);
            coordinate = coordinate.getParent();
        }
        
        Collections.reverse(path);
        path.addFirst(new Point(0, 0));
        
        return path;
    }
    
    public Point solveAlt(int[][] maze, LinkedList<Point> path, int rowNumber, int columnNumber) {
        
        LinkedList<Point> queue = new LinkedList<Point>();
        var initialCoordinate = new Point(0, 0, null);
        
        if (maze[0][0] == 1)
            return null;
            
        queue.add(initialCoordinate);
        
        int x, y;
        while (!queue.isEmpty()) {
            var coordinate = queue.remove();
            
            x = coordinate.getX();
            y = coordinate.getY();
            
            if ((x == rowNumber - 1) && (y == columnNumber - 1))
                return coordinate;
                
            if (checkIfAvailable(x+1, y)) {
                maze[x][y] = 2;
                var nextCoordinate = new Point(x+1, y, coordinate);
                queue.add(nextCoordinate);
            }
            
            if (checkIfAvailable(x, y+1)) {
                maze[x][y] = 2;
                var nextCoordinate = new Point(x, y+1, coordinate);
                queue.add(nextCoordinate);
            }
            
            if (checkIfAvailable(x-1, y)) {
                maze[x][y] = 2;
                var nextCoordinate = new Point(x-1, y, coordinate);
                queue.add(nextCoordinate);
            }
            
            if (checkIfAvailable(x, y-1)) {
                maze[x][y] = 2;
                var nextCoordinate = new Point(x, y-1, coordinate);
                queue.add(nextCoordinate);
            }
        }
        return null;
    }
    
    public boolean checkIfAvailable(int x, int y) {
        if ((x >= 0) && (x < maze.length) && (y >= 0) && (y < maze[x].length) && (maze[x][y] == 0))
            return true;
        return false;
    }
}
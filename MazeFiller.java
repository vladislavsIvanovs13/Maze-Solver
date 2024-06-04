import java.util.LinkedList;
import java.util.Random;
import java.util.Arrays;

public class MazeFiller {
    private int rowNumber;
    private int columnNumber;
    private char answer;

    public MazeFiller(int rowNumber, int columnNumber, char answer) {
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
        this.answer = answer;
    }

    public int[][] fillMaze() {
        var maze = new int[rowNumber][columnNumber];
        if (answer == 'n')
            maze = Console.readMaze(rowNumber, columnNumber, maze);
        else
            maze = generateMaze(rowNumber, columnNumber, maze); 
        return maze;
    }

    public int[][] generateMaze(int rowNumber, int columnNumber, int[][] maze) {
        for (int[] row: maze)
            Arrays.fill(row, 1);

        var seenCells = new boolean[rowNumber][columnNumber];
        var previousCells = new Point[rowNumber][columnNumber];

        LinkedList<Point> stack = new LinkedList<Point>();

        Point start = new Point(0, 0);
        Point end = new Point(rowNumber-1, columnNumber-1);

        stack.add(start);

        while (!stack.isEmpty()) {
            Point coordinate = stack.removeLast();
            int x = coordinate.getX();
            int y = coordinate.getY();
            seenCells[x][y] = true;

            if ((x + 1 < rowNumber) && (maze[x+1][y] == 0) && (!previousCells[x][y].convertCoordinate().equals(new Point(x+1, y).convertCoordinate())))
                continue;
            
            if ((x > 0) && (maze[x-1][y] == 0) && (!previousCells[x][y].convertCoordinate().equals(new Point(x-1, y).convertCoordinate())))
                continue;

            if ((y + 1 < columnNumber) && (maze[x][y+1] == 0) && (!previousCells[x][y].convertCoordinate().equals(new Point(x, y+1).convertCoordinate())))
                continue;

            if ((y > 0) && (maze[x][y-1] == 0) && (!previousCells[x][y].convertCoordinate().equals(new Point(x, y-1).convertCoordinate())))
                continue;

            maze[x][y] = 0;
            LinkedList<Point> shuffler = new LinkedList<Point>();

            if ((x + 1 < rowNumber) && (seenCells[x+1][y] == false)) {
                seenCells[x+1][y] = true;
                shuffler.add(new Point(x+1, y));
                previousCells[x+1][y] = new Point(x, y);
            }

            if ((x > 0) && (seenCells[x-1][y] == false)) {
                seenCells[x-1][y] = true;
                shuffler.add(new Point(x-1, y));
                previousCells[x-1][y] = new Point(x, y);
            }

            if ((y + 1 < columnNumber) && (seenCells[x][y+1] == false)) {
                seenCells[x][y+1] = true;
                shuffler.add(new Point(x, y+1));
                previousCells[x][y+1] = new Point(x, y);
            }

            if ((y > 0) && (seenCells[x][y-1] == false)) {
                seenCells[x][y-1] = true;
                shuffler.add(new Point(x, y-1));
                previousCells[x][y-1] = new Point(x, y);
            }
            
            boolean flag = false;
            Random random = new Random();

            while (shuffler.size() != 0) {
                Point neighbourCell = shuffler.remove(random.nextInt(shuffler.size()));
                if (neighbourCell.equals(end))
                    flag = true;
                else
                    stack.add(neighbourCell);
            }

            if (flag)
                stack.add(end);
        }
        
        return maze;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public int getColumnNumber() {
        return columnNumber;
    }
    
    public char getAnswer() {
        return answer;
    }

    public int[][] getFilledMaze() {
        var maze = fillMaze();
        return maze;
    }
}
import java.util.Scanner;

public class Console {
    private static Scanner scanner = new Scanner(System.in);
    
    public static int readNumber(String prompt) {
        System.out.print(prompt);
        int number = scanner.nextInt();
        return number;
    }

    public static char readRequest(String prompt) {
        System.out.print(prompt);
        char request = scanner.next().charAt(0);
        return request;
    }

    public static int[][] readMaze(int rowNumber, int columnNumber, int[][] maze) {
        for (int i = 0; i < rowNumber; i++)
            for (int j = 0; j < columnNumber; j++)
                maze[i][j] = scanner.nextByte();
        return maze;
    }
}
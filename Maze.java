public class Maze {
    public static void main(String[] args) {
        int rowNumber = Console.readNumber("Rows: ");
        int columnNumber = Console.readNumber("Columns: ");
        char answer = Console.readRequest("Auto fill maze (y/n)? ");
        
        var filler = new MazeFiller(rowNumber, columnNumber, answer);
        var maze = filler.getFilledMaze();
        var printer = new MazePrinter(filler, maze);
        printer.printMaze();

        byte methodNumber = (byte) Console.readNumber("Method number (1-2): ");
        var solver = new MazeSolver(filler, methodNumber, maze);
        printer = new MazePrinter(solver);
        printer.printResults();
    }
}
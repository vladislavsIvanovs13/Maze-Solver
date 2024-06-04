public class Point {
    private int x;
    private int y;
    private Point parent;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public Point(int x, int y, Point parent) {
        this.x = x;
        this.y = y;
        this.parent = parent;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public Point getParent() {
        return parent;
    }

    public String convertCoordinate() {
        String coordinate = "(" + x + "," + y + ")";
        return coordinate;
    }
}
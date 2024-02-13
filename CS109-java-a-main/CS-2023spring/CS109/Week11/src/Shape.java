public class Shape {
    private double x;
    private double y;
    private ShapeColor color = ShapeColor.GRAY;
    private static int screenSize = 10;

    public Shape(double x, double y) {
        this.x = x;
        this.y = y;
    }
    @Override
    public String toString() {
        return " x=" + x + ", y=" + y + ", color=" + color;
    }
}

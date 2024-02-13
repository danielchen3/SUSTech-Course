

public class Circle extends Shape {
    private double radius;
    private double x;
    private double y;
    static final int DEFAULT_RADIUS = 5;
    private static int screenSize = 10;
    private ShapeColor color = ShapeColor.GRAY;

    public Circle(double radius, double x, double y) {
        super(x, y);
        this.radius = radius;
    }

    public Circle(double radius) {
        super(0, 0);
        this.radius = radius;
    }

    public Circle(double x, double y) {
        super(x, y);
        this.radius = DEFAULT_RADIUS;
    }

    public static int getScreenSize() {
        return screenSize;
    }

    public static void setScreenSize(int screenSize) {
        Circle.screenSize = screenSize;
    }

    public void checkColor() {
        if (isInBoundary()) {
            color = ShapeColor.GREEN;
        } else {
            color = ShapeColor.RED;
        }
    }

    public boolean isInBoundary() {
        if (-1 * Circle.screenSize > this.x - this.radius || Circle.screenSize < this.x + this.radius) {
            return false;
        }
        if (-1 * Circle.screenSize > this.y - this.radius || Circle.screenSize < this.y + this.radius) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Circle{" +
                "radius=" + " x=" + x +
                ", y=" + y +
                ", color=" + color +
                "}\n";
    }

    public void draw() {

        StdDraw.setPenColor(color.getColor());
        StdDraw.filledCircle(x, y, radius);

    }
}

import java.text.DecimalFormat;

public class Sphere extends GraphicObject {
    private double radius;

    public Sphere(ObjectColor color, double radius) {
        super(color);
        this.radius = radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public double surfaceMeanSize() {
        return 4 * Math.PI * radius * radius;
    }

    @Override
    public double volume() {
        return (4 * Math.PI * radius * radius * radius) / 3;
    }

    public String toString() {
        DecimalFormat df = new DecimalFormat("#0.00");
        return "Sphere: " + "r=" + df.format(radius);
    }
}

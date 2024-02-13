import java.text.DecimalFormat;

public class Cone extends GraphicObject {
    private double radius;
    private double length; //for all test data, the length is larger than radius

    public Cone(ObjectColor color, double radius, double length) {
        super(color);
        this.radius = radius;
        this.length = length;
    }

    public double getRadius() {
        return radius;
    }

    public double getLength() {
        return length;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public void setLength(double length) {
        this.length = length;
    }

    @Override
    public double surfaceMeanSize() {
        return Math.PI * radius * radius + radius * Math.PI * length;
    }

    @Override
    public double volume() {
        return (Math.PI * radius * radius * (Math.sqrt(length * length - radius * radius))) / 3;
    }

    public String toString() {
        DecimalFormat df = new DecimalFormat("#0.00");
        return "Cone: " + "r=" + df.format(radius) + ", " + "l=" + df.format(length);
    }
}

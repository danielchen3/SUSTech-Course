import java.text.DecimalFormat;

public class Cuboid extends GraphicObject {
    private double x; //The length of cuboid
    private double y; //The width of cuboid
    private double z; //The height of cuboid

    public Cuboid(ObjectColor color, double x, double y, double z) {
        super(color);
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    @Override
    public double surfaceMeanSize() {
        return 2 * (x * y + y * z + z * x);
    }

    @Override
    public double volume() {
        return x * y * z;
    }

    public String toString() {
        DecimalFormat df = new DecimalFormat("#0.00");
        return "Cuboid: " + "x=" + df.format(x) + ", " + "y=" + df.format(y) + ", " + "z=" + df.format(z);
    }
}

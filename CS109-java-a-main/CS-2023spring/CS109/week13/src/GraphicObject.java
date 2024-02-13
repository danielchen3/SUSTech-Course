public abstract class GraphicObject {
    private ObjectColor color;
    private int id;

    public GraphicObject(ObjectColor color) {
        this.color = color;
    }

    public abstract double surfaceMeanSize();

    public abstract double volume();

    public ObjectColor getColor() {
        return color;
    }

    public void setColor(ObjectColor color) {
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

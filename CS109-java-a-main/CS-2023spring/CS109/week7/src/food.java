public class food {
    private int id;
    private String name, type;
    private int size;
    private double price;

    public void set_id(int id) {
        this.id = id;
    }

    public int get_id() {
        return id;
    }

    public void set_name(String name) {
        this.name = name;
    }

    public String get_name() {
        return name;
    }

    public void set_type(String type) {
        this.type = type;
    }

    public String get_type() {
        return type;
    }

    public void set_size(int size) {
        if (size > 0) this.size = size;
    }

    public int get_size() {
        if (size > 0) return size;
        else return 0;
    }

    public void set_price(double price) {
        this.price = price;
    }

    public double get_price() {
        return price;
    }
}

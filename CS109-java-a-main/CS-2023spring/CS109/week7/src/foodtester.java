import java.util.Scanner;

public class foodtester {
    public static void main(String[] args) {
        food eater = new food();
        Scanner input = new Scanner(System.in);
        int id, size;
        String name, type;
        double price;
        id = input.nextInt();
        name = input.next();
        size = input.nextInt();
        type = input.next();
        price = input.nextDouble();
        eater.set_id(id);
        eater.set_name(name);

    }
}

import java.util.Scanner;

public class exercise3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int count = 0;
        while (count < n) {
            count++;
            double v1, m, q, u;
            v1 = input.nextDouble();
            m = input.nextDouble();
            q = input.nextDouble();
            u = input.nextDouble();
            if (0.5 * m * v1 * v1 < q * u) System.out.println("true");
            else System.out.println("false");
        }
    }
}

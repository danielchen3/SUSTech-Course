import java.util.Scanner;

public class ab {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int T = input.nextInt();
        int count = 0;
        while (count < T) {
            count++;
            int a = input.nextInt();
            int b = input.nextInt();
            System.out.println(a + b);
        }
    }
}


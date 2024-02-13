import java.util.Scanner;

public class sequence {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int count = 0;
        while (count < n) {
            count++;
            int a1 = input.nextInt();
            int b1 = input.nextInt();
            int a2 = input.nextInt();
            int b2 = input.nextInt();
            if (a1 == a2) {
                if (b1 + b2 == a1) System.out.println("Yes");
                else System.out.println("No");
            } else if (a1 == b2) {
                if (b1 + a2 == a1) System.out.println("Yes");
                else System.out.println("No");
            } else if (b1 == a2) {
                if (a1 + b2 == b1) System.out.println("Yes");
                else System.out.println("No");
            } else if (b1 == b2) {
                if (a1 + a2 == b1) System.out.println("Yes");
                else System.out.println("No");
            } else System.out.println("No");
        }
    }
}

import java.util.Scanner;

public class exercise4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int T = input.nextInt();
        int out_white = 0, out_black = 0;
        int count = 0;
        while (count < T - 1) {
            count++;
            int color = input.nextInt();
            if (color == 0) out_white++;
            else if (color == 1) out_black++;
        }
        System.out.printf("%.2f\n", (float) (1000 - out_white) / (float) (2000 - out_white - out_black));
    }
}

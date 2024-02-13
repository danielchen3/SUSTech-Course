import java.util.Scanner;

public class Sum {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int T = input.nextInt();
        for (int i = 0; i < T; i++) {
            int n = input.nextInt();
            long ans;
            ans = (long) (n) * (long) (n + 1) * (long) (n + 2) / 6;
            System.out.println(ans);
        }
    }
}

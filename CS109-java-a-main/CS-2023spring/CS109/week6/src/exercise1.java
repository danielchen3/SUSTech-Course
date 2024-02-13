import java.util.Scanner;

public class exercise1 {
    public static void main(String[] args) {
        int T;
        Scanner input = new Scanner(System.in);
        T = input.nextInt();
        int count = 0;
        while (count < T) {
            count++;
            int n, m, flag = 0;
            int[] n1 = new int[101];
            int[] m1 = new int[101];
            int[] ans = new int[202];
            n = input.nextInt();
            for (int i = n - 1; i >= 0; i--)
                n1[i] = input.nextInt();
            m = input.nextInt();
            for (int i = m - 1; i >= 0; i--)
                m1[i] = input.nextInt();
            for (int k = n - 1; k >= 0; k--) {
                for (int l = m - 1; l >= 0; l--) {
                    ans[k + l] += (n1[k] * m1[l]);
                }
            }
            for (int i = n + m - 2; i >= 0; i--) {
                if (i == 0) System.out.println(ans[0]);
                else System.out.printf("%d ", ans[i]);
            }
        }
    }
}
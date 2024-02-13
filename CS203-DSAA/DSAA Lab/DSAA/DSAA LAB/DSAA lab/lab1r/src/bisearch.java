import java.util.Arrays;
import java.util.Scanner;

public class bisearch {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = input.nextInt();
        //Arrays.sort(a);
        int T = input.nextInt();
        for (int i = 0; i < T; i++) {
            boolean judge = false;
            int x = input.nextInt();
            int left = 0, right = n - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (x == a[mid]) {
                    judge = true;
                    break;
                } else if (x > a[mid])
                    left = mid + 1;
                else right = mid - 1;
            }
            if (judge) System.out.println("YES");
            else System.out.println("NO");
        }
    }
}

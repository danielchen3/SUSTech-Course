import java.util.Arrays;
import java.util.Scanner;

public class sport {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (input.hasNext()) {
            int L = input.nextInt();
            int n = input.nextInt();
            int m = input.nextInt();
            int[] a = new int[n + 2];
            for (int i = 1; i < n + 1; i++) a[i] = input.nextInt();
            a[0] = 0;
            a[n + 1] = L;
            Arrays.sort(a);
            int[] t = new int[n + 1];
            for (int i = 0; i < n + 1; i++)
                t[i] = a[i + 1] - a[i];
            long left = 0;
            long right = Long.MAX_VALUE;
            while (left < right) {
                long mid = (left + right) / 2;
                int grp = 0;
                int sum = 0;
                boolean judge = false;
                for (int i = 0; i < n + 1; i++) {
                    if ((long) t[i] > mid) {
                        judge = true;
                        break;
                    }
                    sum += (long) t[i];
                    if (sum > mid) {
                        grp++;
                        sum = t[i];
                    }
                }
                grp++;
                if (grp > m || judge) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            System.out.println(right);
        }
        input.close();
    }
}

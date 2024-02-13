import java.util.Arrays;
import java.util.Scanner;

public class Sim {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        int Q = input.nextInt();
        int[] a = new int[N];
        for (int i = 0; i < N; i++)
            a[i] = input.nextInt();
        for (int i = 0; i < Q; i++) {
            int x = input.nextInt();
            int y = input.nextInt();
            if (y <= x || x >= a[N - 1] || y <= a[0]) {
                System.out.println("NO");
            } else {
                int left = find_x(0, N - 1, a, x);
                int right = find_y(0, N - 1, a, y);
                if (left > right) System.out.println("NO");
                else System.out.println("YES " + (right - left + 1));
            }
        }
    }

    protected static int find_x(int left, int right, int a[], int xy) {
        int ans = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (a[mid] <= xy) {
                left = mid + 1;
            } else {
                ans = mid;
                right = mid - 1;
            }
        }
        return ans;
    }


    protected static int find_y(int left, int right, int a[], int xy) {
        int ans = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (a[mid] >= xy) {
                right = mid - 1;
            } else {
                ans = mid;
                left = mid + 1;
            }
        }
        return ans;
    }
}

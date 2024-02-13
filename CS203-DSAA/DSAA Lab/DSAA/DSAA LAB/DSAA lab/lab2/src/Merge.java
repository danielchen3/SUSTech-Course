import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Merge {
    public static void main(String[] args) {
        QReader qr = new QReader();
        QWriter qw = new QWriter();
        Scanner input = new Scanner(System.in);
        int T = qr.nextInt();
        for (int i = 0; i < T; i++) {
            int n = qr.nextInt();
            int m = qr.nextInt();
            int[] arr = new int[n + m + 10];
            for (int j = 0; j < n; j++) arr[j] = qr.nextInt();
            for (int j = n; j < n + m; j++) arr[j] = qr.nextInt();
            int[] ans = new int[n + m + 10];
            int tmp1 = 0;
            int tmp2 = n;
            int pts = 0;
            while (tmp1 < n && tmp2 < m + n) {
                if (arr[tmp1] > arr[tmp2]) ans[pts++] = arr[tmp2++];
                else ans[pts++] = arr[tmp1++];
            }
            while (tmp1 < n) ans[pts++] = arr[tmp1++];
            while (tmp2 < n + m) ans[pts++] = arr[tmp2++];
            for (int j = 0; j < m + n; j++) {
                if (j != m + n - 1) qw.print(ans[j] + " ");
                else
                    qw.println(ans[j]);
            }
        }
        qw.close();
    }
}

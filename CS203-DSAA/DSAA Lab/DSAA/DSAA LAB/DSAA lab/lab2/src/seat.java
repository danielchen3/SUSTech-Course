import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class seat {

    public static int[] arr = new int[10000000];

    public static int[] temp = new int[10000000];

    public static int[] ans = new int[10000000];

    public static void main(String[] args) {
        //Scanner input = new Scanner(System.in);
        QReader input = new QReader();
        QWriter out = new QWriter();
        int n = input.nextInt();
        int res;
        for (int i = 0; i < n; i++) arr[i] = input.nextInt();
        for (int i = 0; i < n; i++) ans[i] = -1;
        MergeSort(0, n - 1);
        res = arr[n / 3];
        if (n % 3 == 0) {
            int cnt = 0;
            int sta = 0;
            while (cnt < n && arr[sta] < res) {
                ans[cnt] = arr[sta];
                cnt += 3;
                sta++;
            }
            for (int i = 0; i < n; i++) {
                if (ans[i] == -1) ans[i] = arr[sta++];
            }
            if (ans[1] == ans[0]) {
                for (int i = 0; i < n; i++) ans[i] = arr[i];
            }
        } else if (n % 3 == 1) {
            int cnt = 0;
            int sta = 0;
            while (cnt < n) {
                ans[cnt] = arr[sta];
                cnt += 3;
                sta++;
            }
            int tag = 0;
            for (int i = n - 1; i > 0; i -= 3) {
                if (ans[i] != res) {
                    tag = i;
                    break;
                } else ans[i] = -1;
            }
            int tmp = tag / 3 + 1;
            for (int i = 1; i < n; i++) {
                if (ans[i] == -1) ans[i] = arr[tmp++];
            }
        } else {
            int cnt = 0;
            int sta = 0;
            while (cnt < n) {
                ans[cnt] = arr[sta];
                cnt += 3;
                sta++;
            }
            int tag = 0;
            for (int i = n - 2; i > 0; i -= 3) {
                if (ans[i] != res) {
                    tag = i;
                    break;
                } else ans[i] = -1;
            }
            int tmp = tag / 3 + 1;
            for (int i = 1; i < n; i++) {
                if (ans[i] == -1) ans[i] = arr[tmp++];
            }
        }
        out.println(res);
        for (int i = 0; i < n; i++) {
            if (i != n - 1) out.print(ans[i] + " ");
            else out.println(ans[i]);
        }
        out.close();
    }

    protected static int getmedian(int a, int b, int c) {
        if ((a <= b && b <= c) || (c <= b && b <= a)) return b;
        if ((b <= a && a <= c) || (c <= a && a <= b)) return a;
        else return c;
    }

    protected static void MergeSort(int left, int right) {
        if (left < right) {
            int mid = (left + right) >> 1;
            MergeSort(left, mid);
            MergeSort(mid + 1, right);
            merge(left, mid, right);
        }
    }

    protected static void merge(int left, int mid, int right) {
        int leftpos = left;
        int rightpos = mid + 1;
        int temppos = left;
        while (leftpos <= mid && rightpos <= right) {
            if (arr[leftpos] <= arr[rightpos]) temp[temppos++] = arr[leftpos++];
            else {
                temp[temppos++] = arr[rightpos++];
            }
        }
        while (leftpos <= mid) {
            temp[temppos++] = arr[leftpos++];
        }
        while (rightpos <= right) {
            temp[temppos++] = arr[rightpos++];
        }
        for (int i = left; i <= right; i++)
            arr[i] = temp[i];
    }
}
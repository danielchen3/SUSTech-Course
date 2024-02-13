import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class median {

    public static int[] arr = new int[10000001];

    public static int[] temp = new int[10000001];

    public static void main(String[] args) {
        QReader input = new QReader();
        QWriter out = new QWriter();
        //Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        for (int i = 0; i < n; i++) arr[i] = input.nextInt();
        MergeSort(0, n - 1);
        long ans = 0;
        if (n % 2 == 0) ans = (long) arr[n / 2 - 1] + (long) arr[n / 2];
        else ans = (long) arr[n / 2] * 2;
        out.println(ans);
        out.close();
    }

    protected static void MergeSort(int left, int right) {
        if (left >= right) return;
        int mid = (left + right) / 2;
        MergeSort(left, mid);
        MergeSort(mid + 1, right);
        merge(left, mid, right);
    }

    protected static void merge(int left, int mid, int right) {
        int leftpos = left;
        int rightpos = mid + 1;
        int temppos = left;

        //分成两组，并且按照大小顺序加入新的数组
        while (leftpos <= mid && rightpos <= right) {
            if (arr[leftpos] < arr[rightpos]) temp[temppos++] = arr[leftpos++];
            else temp[temppos++] = arr[rightpos++];
        }

        //把还没加进去的数字加进去
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
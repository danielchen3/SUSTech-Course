import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class bubble {

    public static long ans = 0;
    public static int[] arr = new int[1000000];

    public static int[] temp = new int[1000000];

    public static void main(String[] args) {
        //Scanner input=new Scanner(System.in);
        QReader input = new QReader();
        QWriter out = new QWriter();
        int T = input.nextInt();
        for (int i = 0; i < T; i++) {
            int n = input.nextInt();
            for (int j = 0; j < n; j++)
                arr[j] = input.nextInt();
            ans = 0;
            MergeSort(0, n - 1);
            out.println(ans);
        }
        out.close();
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
                ans += (long) mid + (long) 1 - (long) leftpos;
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

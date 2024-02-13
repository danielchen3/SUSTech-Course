import java.io.*;
import java.util.StringTokenizer;

public class Cutstickv2 {
    public static int[] arr = new int[10001];
    public static int[] temp = new int[10001];

    public static int ans = 0;

    public static void main(String[] args) {
        QReader input = new QReader();
        QWriter output = new QWriter();

        int T = input.nextInt();

        for (int i = 0; i < T; i++) {
            ans = 0;
            int N = input.nextInt();
            for (int j = 0; j < N; j++) {
                arr[j] = input.nextInt();
            }
            MergeSort(0, N - 1);
            for (int j = 0; j < N - 1; j++) {
                int sum = arr[j] + arr[j + 1];
                int temp = j + 2;
                //前移
                while (temp < N && sum > arr[temp]) {
                    arr[temp - 1] = arr[temp];
                    temp++;
                }
                arr[temp - 1] = sum;
                ans += sum;
            }
            output.println(ans);
        }

        output.close();
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

class Stick_Node {
    int val;

    public Stick_Node(int val) {
        this.val = val;
    }

    Stick_Node next;

}
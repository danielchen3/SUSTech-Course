import java.util.Scanner;

public class CutStick {

    public static int[] arr = new int[10001];
    public static int[] temp = new int[10001];

    public static long ans = 0;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int T = input.nextInt();

        for (int i = 0; i < T; i++) {
            long sum = 0;
            ans = 0;
            int n = input.nextInt();
            arr = new int[10001];
            temp = new int[10001];
            for (int j = 0; j < n; j++) {
                arr[j] = input.nextInt();
                sum += arr[j];
            }
            if (n != 1) {
                MergeSort(0, n - 1);
                cut_stick(0, n - 1, sum);
            }
            System.out.println(ans);
        }
    }

    protected static void cut_stick(int left, int right, long sum) {
        if (left >= right) return;
        ans += sum;
        long ss = 0;
        int count = left;
        for (int i = left; i <= right; i++) {
            ss += arr[i];
            if (ss <= sum / 2 && (ss + arr[i + 1] > sum / 2)) {
                if (Math.abs(ss - sum + ss) <= Math.abs(ss + arr[i + 1] - sum + ss + arr[i + 1])) {
                    count = i;
                    break;
                } else {
                    count = i + 1;
                    ss += arr[i + 1];
                    break;
                }
            }
        }
        cut_stick(count + 1, right, sum - ss);
        cut_stick(left, count, ss);
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

public class paytoswap {

    public static int[] arr = new int[1000000];

    public static int[] temp = new int[1000000];

    public static long ans = 0;


    public static void main(String[] args) {
        QReader input = new QReader();
        QWriter output = new QWriter();
        int n = input.nextInt();
        for (int i = 0; i < n; i++)
            arr[i] = input.nextInt();


    }

    public static int get_min(int a, int b) {
        return Math.min(a, b);
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
                //ans += (long) mid + (long) 1 - (long) leftpos;
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

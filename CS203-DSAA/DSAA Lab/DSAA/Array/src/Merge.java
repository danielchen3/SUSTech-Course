public class Merge {
    protected void MergeSort(int arr[], int left, int right) {
        if (left >= right) return;
        int mid = (left + right) / 2;
        MergeSort(arr, left, mid);
        MergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    protected void merge(int arr[], int left, int mid, int right) {
        int[] temp = new int[arr.length];
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

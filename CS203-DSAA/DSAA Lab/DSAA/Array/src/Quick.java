import java.util.Arrays;

public class Quick {
    protected void QuickSort(int[] arr, int left, int right) {
        if (left >= right) return;
        int leftpos = left;
        int rightpos = right;
        int tag = arr[left];
        while (leftpos < rightpos) {
            while (leftpos < rightpos && arr[rightpos] >= tag) rightpos--;
            if (leftpos < rightpos) arr[leftpos] = arr[rightpos];
            while (leftpos < rightpos && arr[leftpos] <= tag) leftpos++;
            if (leftpos < rightpos) arr[rightpos] = arr[leftpos];
        }
        arr[leftpos] = arr[rightpos] = tag;
        QuickSort(arr, left, leftpos - 1);
        QuickSort(arr, rightpos + 1, right);
    }
}

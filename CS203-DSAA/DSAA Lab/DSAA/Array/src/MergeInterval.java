import java.util.Arrays;
import java.util.Comparator;

public class MergeInterval {

    protected void QuickSort(int[][] arr, int left, int right) {
        if (left >= right) return;
        int leftpos = left;
        int rightpos = right;
        int tag = arr[left][0];
        while (leftpos < rightpos) {
            while (leftpos < rightpos && arr[rightpos][0] >= tag) rightpos--;
            if (leftpos < rightpos) arr[leftpos][0] = arr[rightpos][0];
            while (leftpos < rightpos && arr[leftpos][0] <= tag) leftpos++;
            if (leftpos < rightpos) arr[rightpos] = arr[leftpos];
        }
        arr[leftpos] = arr[rightpos] = arr[left];
        QuickSort(arr, left, leftpos - 1);
        QuickSort(arr, rightpos + 1, right);
    }

    public int[][] Merge(int[][] intervals) {
        int[][] ans = new int[10001][2];
        QuickSort(intervals,0,intervals.length-1);
        int i = 0;
        int tag = 0;
        int num = 0;
        while (i < intervals.length - 1) {
            if (intervals[tag][0] > intervals[tag + 1][1]) {
                tag++;
            } else {
                int max = 0;
                for (int j = i; j <= tag; j++) {
                    max = Math.max(max, intervals[j][1]);
                }
                ans[num][0] = intervals[i][0];
                ans[num][1] = max;
                num++;
                i = tag + 1;
            }
        }
        return intervals;
    }
}

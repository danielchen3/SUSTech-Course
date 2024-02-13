import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ThreeFigureSum {

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

    public List<List<Integer>> threeSum(int[] nums) {
        QuickSort(nums, 0, nums.length - 1);
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int leftpos = i + 1;
            int rightpos = nums.length - 1;
            while (leftpos < rightpos) {
                int sum = nums[i] + nums[leftpos] + nums[rightpos];
                if (sum < 0) leftpos++;
                else if (sum > 0) rightpos--;
                else {
                    ans.add(Arrays.asList(nums[i], nums[leftpos], nums[rightpos]));
                    while (leftpos < rightpos && nums[leftpos] == nums[leftpos + 1]) leftpos++;
                    while (leftpos < rightpos && nums[rightpos] == nums[rightpos - 1]) rightpos--;
                    leftpos++;
                    rightpos--;
                }
            }
        }
        return ans;
    }
}

import java.util.Arrays;
import java.util.Scanner;


public class p16 {

    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int ans = (int) 1e7;
        for (int i = 0; i < n; i++) {
            int left = i + 1, right = n - 1;
            while (left < right) {
                int sum = nums[left] + nums[right] + nums[i];
                if (Math.abs(sum - target) < Math.abs(ans - target))
                    ans = sum;
                if (sum == target) break;
                else if (sum < target) left++;
                else right--;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = input.nextInt();
        int target = input.nextInt();
        System.out.println(threeSumClosest(nums, target));
    }
}

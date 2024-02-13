import java.util.Scanner;

public class p34 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = input.nextInt();
        int target = input.nextInt();
        int[] ans = searchRange(nums, target);
        for (int i = 0; i < 2; i++)
            System.out.println(ans[i]);
    }


    public static int[] searchRange(int[] nums, int target) {
        int[] ans = new int[2];
        if (nums.length == 0) {
            ans[0] = -1;
            ans[1] = -1;
        } else if (nums.length == 1) {
            if (nums[0] != target) {
                ans[0] = -1;
                ans[1] = -1;
            }
        } else {
            int left = 0, right = nums.length - 1;
            int get_position = -1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (nums[mid] == target) {
                    get_position = mid;
                    break;
                }
                if (nums[mid] < target) left = mid + 1;
                else right = mid - 1;
            }
            if (get_position == -1) {
                ans[0] = -1;
                ans[1] = -1;
            } else {
                int get_left = get_position;
                int get_right = get_position;
                while (true) {
                    if (nums[get_left] != target) {
                        get_left++;
                        break;
                    } else get_left--;
                    if (get_left == -1) {
                        get_left++;
                        break;
                    }
                }
                while (true) {
                    if (nums[get_right] != target) {
                        get_right--;
                        break;
                    } else get_right++;
                    if (get_right == nums.length) {
                        get_right--;
                        break;
                    }
                }
                ans[0] = get_left;
                ans[1] = get_right;
            }
        }
        return ans;
    }
}

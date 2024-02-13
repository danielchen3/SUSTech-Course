import java.util.Scanner;

public class p33 {


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = input.nextInt();
        int target = input.nextInt();
        System.out.println(search(nums, target));
    }

    public static int search(int[] nums, int target) {
        int n = nums.length;
        int left = 0, right = n - 1;
        int get_rotate_pos = -1;
        int ans = 5001;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] > nums[mid + 1]) {
                get_rotate_pos = mid;
                break;
            }
            if (nums[mid] < nums[n - 1]) right = mid;
            else left = mid + 1;
        }
        if (get_rotate_pos >= 0) {
            if (target < nums[get_rotate_pos] && target >= nums[0]) {
                int l = 0, r = get_rotate_pos;
                while (l <= r) {
                    int mid = (l + r) / 2;
                    if (target > nums[mid]) l = mid + 1;
                    else if (target < nums[mid]) r = mid - 1;
                    else {
                        ans = mid;
                        break;
                    }
                }
            } else if (target == nums[get_rotate_pos]) ans = get_rotate_pos;
            else {
                int l = get_rotate_pos + 1, r = n - 1;
                while (l <= r) {
                    int mid = (l + r) / 2;
                    if (target > nums[mid]) l = mid + 1;
                    else if (target < nums[mid]) r = mid - 1;
                    else {
                        ans = mid;
                        break;
                    }
                }
            }
        } else {
            int l = 0, r = n - 1;
            while (l <= r) {
                int mid = (l + r) / 2;
                if (target > nums[mid]) l = mid + 1;
                else if (target < nums[mid]) r = mid - 1;
                else {
                    ans = mid;
                    break;
                }
            }
        }
        if (ans == 5001) ans = -1;
        return ans;
    }
}

import java.util.Arrays;
import java.util.Scanner;

public class p31 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = input.nextInt();
        nextPermutation(nums);
        for (int i = 0; i < n; i++) {
            if (i != n - 1) System.out.printf(nums[i] + " ");
            else System.out.println(nums[i]);
        }
    }

    public static void nextPermutation(int[] nums) {
        int n = nums.length;
        boolean is_descending = true;
        for (int i = 0; i < n - 1; i++) {
            if (nums[i + 1] > nums[i]) {
                is_descending = false;
                break;
            }
        }
        if (is_descending) {
            for (int i = 0; i < n / 2; i++) {
                int tmp = nums[i];
                nums[i] = nums[n - 1 - i];
                nums[n - 1 - i] = tmp;
            }
        } else {
            int get_pos = 0;
            for (int i = n - 1; i >= 1; i--) {
                if (nums[i - 1] < nums[i]) {
                    get_pos = i - 1;
                    break;
                }
            }
            for (int i = get_pos + 1; i < (n + get_pos + 1) / 2; i++) {
                int tmp = nums[i];
                nums[i] = nums[n + get_pos - i];
                nums[n + get_pos - i] = tmp;
            }
            for (int i = get_pos + 1; i < n; i++) {
                if (nums[get_pos] < nums[i]) {
                    int tmp = nums[get_pos];
                    nums[get_pos] = nums[i];
                    nums[i] = tmp;
                    break;
                }
            }
        }
    }
}

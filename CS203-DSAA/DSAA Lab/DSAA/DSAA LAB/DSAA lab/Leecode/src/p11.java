import java.util.Scanner;


public class p11 {


    public static int maxArea(int[] height) {
        int ans = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            int count_size = Math.min(height[left], height[right]) * (right - left);
            ans = Math.max(count_size, ans);
            if (height[left] < height[right]) left++;
            else right--;
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] height = new int[n];
        for (int i = 0; i < n; i++) height[i] = input.nextInt();
        System.out.println(maxArea(height));
    }
}

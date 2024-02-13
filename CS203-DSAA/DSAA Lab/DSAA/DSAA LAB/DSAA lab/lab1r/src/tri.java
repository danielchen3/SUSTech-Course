import java.util.Scanner;

public class tri {
    //public static int ans = 0;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        long S = input.nextInt();
        long ans = 0;
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = input.nextInt();
        for (int i = 0; i < n - 1; i++) {
            int left = i + 1;
            int right = n - 1;
            while (left < right) {
                long sum = (long) (a[i] + a[left]) + (long) a[right];
                if (sum < S) {
                    left++;
                } else if (sum > S) {
                    right--;
                } else {
                    if (a[left] != a[right]) {
                        int tmp1 = 0, tmp2 = 0;
                        int r = right;
                        int l = left;
                        while (r > l + 1 && a[r] == a[r - 1]) {
                            tmp1++;
                            r--;
                        }
                        r = right;
                        l = left;
                        while (r > l + 1 && a[l] == a[l + 1]) {
                            tmp2++;
                            l++;
                        }
                        ans += (long) (tmp1 + 1) * (long) (tmp2 + 1);
                        right -= (tmp1 + 1);
                        left += (tmp2 + 1);
                    } else {
                        ans += (long) (right - left + 1) * (long) (right - left) / 2;
                        break;
                    }
                }
            }
        }
        System.out.println(ans);
    }
}

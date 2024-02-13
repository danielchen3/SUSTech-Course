import java.util.Scanner;

public class MaxiDiff {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int T = input.nextInt();
        for (int i = 0; i < T; i++) {
            int n = input.nextInt();
            int[] a = new int[n];
            for (int j = 0; j < n; j++) a[j] = input.nextInt();
            System.out.println(findMaxDifference(a));
        }
    }

    public static int findMaxDifference(int[] arr) {
        if (arr.length < 2) {
            return -1;
        }
        int maxDiff = Integer.MIN_VALUE;
        int[] find_max = new int[200001];
        int[] find_min = new int[200001];
        find_max[0] = arr[0];
        find_min[arr.length - 1] = arr[arr.length - 1];
        for (int i = 1; i < arr.length; i++)
            find_max[i] = Math.max(find_max[i - 1], arr[i]);
        for (int i = arr.length - 2; i > 0; i--)
            find_min[i] = Math.min(find_min[i + 1], arr[i]);
        for (int i = 0; i < arr.length - 2; i++)
            maxDiff = Math.max(find_max[i] - find_min[i + 1], maxDiff);
        return maxDiff;
    }
}

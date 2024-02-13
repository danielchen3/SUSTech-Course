import java.util.Scanner;

public class Matrix {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int T = input.nextInt();
        for (int i = 0; i < T; i++) {
            int n = input.nextInt();
            int m = input.nextInt();
            int[][] arr = new int[n + 1][n + 1];
            for (int j = 1; j <= m; j++) {
                int x = input.nextInt();
                int y = input.nextInt();
                arr[x - 1][y - 1] = 1;
            }
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    System.out.print(arr[j][k] + " ");
                }
                System.out.println();
            }
        }
    }
}

import java.io.IOException;
import java.util.Scanner;

public class exercise2 {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        int n, m, k;
        char[][] land = new char[101][101];
        int[][] flag = new int[101][101];
        n = input.nextInt();
        m = input.nextInt();
        k = input.nextInt();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                land[i][j] = input.next().charAt(0);
                if (land[i][j] == 'B') flag[i][j] = 1;
            }
        }
        int count = 0;
        while (count < k) {
            count++;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (land[i][j] == 'B') flag[i][j] = 1;
                }
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (land[i][j] == 'B' && flag[i][j] == 1) {
                        if (i != 0) {
                            if (land[i - 1][j] == 'L') {
                                land[i - 1][j] = 'B';
                                flag[i - 1][j] = 2;
                            }
                        }
                        if (j != 0) {
                            if (land[i][j - 1] == 'L') {
                                land[i][j - 1] = 'B';
                                flag[i][j - 1] = 2;
                            }
                        }
                        if (j != m - 1) {
                            if (land[i][j + 1] == 'L') {
                                land[i][j + 1] = 'B';
                                flag[i][j + 1] = 2;
                            }
                        }
                        if (i != n - 1) {
                            if (land[i + 1][j] == 'L') {
                                land[i + 1][j] = 'B';
                                flag[i + 1][j] = 2;
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++)
                System.out.printf("%c ", land[i][j]);
            System.out.println();
        }
    }
}

import java.util.Scanner;

public class redalert {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int m = input.nextInt();
        int k = input.nextInt();
        int[][] flag = new int[210][210];
        int ans = 0;
        for (int i = 0; i < m; i++) {
            int x = input.nextInt();
            int y = input.nextInt();
            desolator(x + 55, y + 55, flag);
        }
        for (int j = 0; j < k; j++) {
            int o = input.nextInt();
            int p = input.nextInt();
            cannon(o + 55, p + 55, flag);
        }
        for (int i = 55; i < 55 + n; i++) {
            for (int j = 55; j < 55 + n; j++) {
                if (flag[i][j] == 0) ans++;
            }
        }
        System.out.println(ans);
    }

    public static void desolator(int x, int y, int[][] flag) {
        flag[x - 1][y] = flag[x - 1][y - 1] = flag[x - 1][y + 1] = flag[x][y] = flag[x][y - 1] = flag[x][y + 1] = flag[x + 1][y + 1] = flag[x + 1][y] = flag[x + 1][y - 1] = 1;
    }

    public static void cannon(int x, int y, int[][] flag) {
        flag[x - 2][y - 1] = flag[x - 2][y + 1] = flag[x - 1][y - 2] = flag[x - 1][y + 2] = flag[x + 1][y - 2] = flag[x + 1][y + 2] = flag[x + 2][y - 1] = flag[x + 2][y + 1] = flag[x - 2][y] = flag[x - 2][y - 2] = flag[x - 2][y + 2] = flag[x][y] = flag[x][y - 2] = flag[x][y + 2] = flag[x + 2][y + 2] = flag[x + 2][y] = flag[x + 2][y - 2] = 1;
    }
}

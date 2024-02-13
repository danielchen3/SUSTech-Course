import java.util.Scanner;

public class sweeper {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        char[][] t = new char[102][102];
        String[] s = new String[102];
        for (int i = 0; i < n; i++)
            s[i] = input.next();
        for (int i = 0; i < n; i++) {
            char[] temp = s[i].toCharArray();
            for (int j = 0; j < n; j++) {
                t[i][j] = temp[j];
            }
        }
        int c = input.nextInt();
        int d = input.nextInt();
        int ans = scan(n, c, d, t);
        System.out.println(ans);
    }

    public static int scan(int bound, int x, int y, char b[][]) {
        int ans = 0;
        if (b[x][y] == 'x') ans = -1;
        else if (bound == 1) {
            if (b[x][y] == 'x') ans = -1;
            else ans = 0;
        } else {
            if (x - 1 >= 0 && x + 1 < bound && y - 1 >= 0 && y + 1 < bound) {
                if (b[x - 1][y - 1] == 'x') ans++;
                if (b[x - 1][y] == 'x') ans++;
                if (b[x - 1][y + 1] == 'x') ans++;
                if (b[x][y - 1] == 'x') ans++;
                if (b[x][y] == 'x') ans++;
                if (b[x][y + 1] == 'x') ans++;
                if (b[x + 1][y - 1] == 'x') ans++;
                if (b[x + 1][y] == 'x') ans++;
                if (b[x + 1][y + 1] == 'x') ans++;
            }
            if (x - 1 < 0) {
                if (y + 1 >= bound) {
                    if (b[x][y] == 'x') ans++;
                    if (b[x][y - 1] == 'x') ans++;
                    if (b[x + 1][y] == 'x') ans++;
                    if (b[x + 1][y - 1] == 'x') ans++;
                } else if (y - 1 < 0) {
                    if (b[x][y] == 'x') ans++;
                    if (b[x][y + 1] == 'x') ans++;
                    if (b[x + 1][y] == 'x') ans++;
                    if (b[x + 1][y + 1] == 'x') ans++;
                } else {
                    if (b[x][y - 1] == 'x') ans++;
                    if (b[x][y] == 'x') ans++;
                    if (b[x][y + 1] == 'x') ans++;
                    if (b[x + 1][y - 1] == 'x') ans++;
                    if (b[x + 1][y] == 'x') ans++;
                    if (b[x + 1][y + 1] == 'x') ans++;
                }
            }
            if (x + 1 >= bound) {
                if (y + 1 >= bound) {
                    if (b[x - 1][y - 1] == 'x') ans++;
                    if (b[x - 1][y] == 'x') ans++;
                    if (b[x][y - 1] == 'x') ans++;
                    if (b[x][y] == 'x') ans++;
                } else if (y - 1 < 0) {
                    if (b[x - 1][y] == 'x') ans++;
                    if (b[x - 1][y + 1] == 'x') ans++;
                    if (b[x][y] == 'x') ans++;
                    if (b[x][y + 1] == 'x') ans++;
                } else {
                    if (b[x - 1][y - 1] == 'x') ans++;
                    if (b[x - 1][y] == 'x') ans++;
                    if (b[x - 1][y + 1] == 'x') ans++;
                    if (b[x][y - 1] == 'x') ans++;
                    if (b[x][y] == 'x') ans++;
                    if (b[x][y + 1] == 'x') ans++;
                }
            }
            if (x + 1 < bound && x - 1 >= 0) {
                if (y + 1 >= bound) {
                    if (b[x - 1][y - 1] == 'x') ans++;
                    if (b[x - 1][y] == 'x') ans++;
                    if (b[x][y - 1] == 'x') ans++;
                    if (b[x][y] == 'x') ans++;
                    if (b[x + 1][y - 1] == 'x') ans++;
                    if (b[x + 1][y] == 'x') ans++;
                } else if (y - 1 < 0) {
                    if (b[x - 1][y] == 'x') ans++;
                    if (b[x - 1][y + 1] == 'x') ans++;
                    if (b[x][y] == 'x') ans++;
                    if (b[x][y + 1] == 'x') ans++;
                    if (b[x + 1][y] == 'x') ans++;
                    if (b[x + 1][y + 1] == 'x') ans++;
                }
            }
        }
        return ans;
    }
}

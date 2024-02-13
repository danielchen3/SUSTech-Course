import java.util.Scanner;

public class tetris {
    public static int w;
    public static int h;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        w = input.nextInt();
        h = input.nextInt();
        int n = input.nextInt();
        int[][] ans = new int[22][22];
        boolean judge = false;
        for (int i = 0; i < n; i++) {
            char s;
            int t;
            s = input.next().charAt(0);
            t = input.nextInt();
            if (s == 'I' && judge == false) {
                add_I(t, 10, ans);
                if (check_out(ans)) judge = true;
            }
            if (s == 'O' && judge == false) {
                add_O(t, 10, ans);
                if (check_out(ans)) judge = true;
            }
            if (s == 'L' && judge == false) {
                add_L(t, 10, ans);
                if (check_out(ans)) judge = true;
            }
            if (s == 'J' && judge == false) {
                add_J(t, 10, ans);
                if (check_out(ans)) judge = true;
            }
            if (s == 'T' && judge == false) {
                add_T(t, 10, ans);
                if (check_out(ans)) judge = true;
            }
            if (s == 'S' && judge == false) {
                add_S(t, 10, ans);
                if (check_out(ans)) judge = true;
            }
            if (s == 'Z' && judge == false) {
                add_Z(t, 10, ans);
                if (check_out(ans)) judge = true;
            }
        }
        for (int i = 10; i < 10 + h; i++) {
            for (int j = 0; j < w; j++) {
                System.out.printf("%d", ans[i][j]);
            }
            System.out.println();
        }
    }

    public static void check_eliminate(int pos, int[][] ans) {
        int sum = 0;
        for (int i = 0; i < w; i++) {
            if (ans[pos][i] == 1) sum++;
        }
        if (sum == w) {
            for (int j = 0; j < w; j++)
                ans[pos][j] = 0;
            for (int i = pos - 1; i > 0; i--) {
                for (int j = 0; j < w; j++) {
                    if (ans[i][j] == 1) {
                        ans[i][j] = 0;
                        ans[i + 1][j] = 1;
                    }
                }
            }
        }
    }

    public static boolean check_out(int[][] ans) {
        boolean judge = false;
        for (int i = 0; i < w; i++) {
            if (ans[9][i] == 1) {
                judge = true;
                break;
            }
        }
        return judge;
    }

    public static void add_I(int t, int pos, int[][] ans) {
        if (pos < 10 + h) {
            if (ans[pos][t] == 0 && ans[pos][t + 1] == 0 && ans[pos][t + 2] == 0 && ans[pos][t + 3] == 0)
                add_I(t, pos + 1, ans);
            else {
                ans[pos - 1][t] = ans[pos - 1][t + 1] = ans[pos - 1][t + 2] = ans[pos - 1][t + 3] = 1;
                check_eliminate(pos - 1, ans);
            }
        } else {
            ans[pos - 1][t] = ans[pos - 1][t + 1] = ans[pos - 1][t + 2] = ans[pos - 1][t + 3] = 1;
            check_eliminate(pos - 1, ans);
        }
    }

    public static void add_O(int t, int pos, int[][] ans) {
        if (pos < 10 + h) {
            if (ans[pos][t] == 0 && ans[pos][t + 1] == 0)
                add_O(t, pos + 1, ans);
            else {
                ans[pos - 1][t] = ans[pos - 1][t + 1] = ans[pos - 2][t] = ans[pos - 2][t + 1] = 1;
                check_eliminate(pos - 2, ans);
                check_eliminate(pos - 1, ans);
            }
        } else {
            ans[pos - 1][t] = ans[pos - 1][t + 1] = ans[pos - 2][t] = ans[pos - 2][t + 1] = 1;
            check_eliminate(pos - 2, ans);
            check_eliminate(pos - 1, ans);
        }
    }

    public static void add_L(int t, int pos, int[][] ans) {
        if (pos < 10 + h) {
            if (ans[pos][t] == 0 && ans[pos][t + 1] == 0)
                add_L(t, pos + 1, ans);
            else {
                ans[pos - 1][t] = ans[pos - 1][t + 1] = ans[pos - 2][t] = ans[pos - 3][t] = 1;
                check_eliminate(pos - 3, ans);
                check_eliminate(pos - 2, ans);
                check_eliminate(pos - 1, ans);
            }
        } else {
            ans[pos - 1][t] = ans[pos - 1][t + 1] = ans[pos - 2][t] = ans[pos - 3][t] = 1;
            check_eliminate(pos - 3, ans);
            check_eliminate(pos - 2, ans);
            check_eliminate(pos - 1, ans);
        }
    }

    public static void add_J(int t, int pos, int[][] ans) {
        if (pos < 10 + h) {
            if (ans[pos][t] == 0 && ans[pos][t + 1] == 0)
                add_J(t, pos + 1, ans);
            else {
                ans[pos - 1][t] = ans[pos - 1][t + 1] = ans[pos - 2][t + 1] = ans[pos - 3][t + 1] = 1;
                check_eliminate(pos - 3, ans);
                check_eliminate(pos - 2, ans);
                check_eliminate(pos - 1, ans);
            }
        } else {
            ans[pos - 1][t] = ans[pos - 1][t + 1] = ans[pos - 2][t + 1] = ans[pos - 3][t + 1] = 1;
            check_eliminate(pos - 3, ans);
            check_eliminate(pos - 2, ans);
            check_eliminate(pos - 1, ans);
        }
    }

    public static void add_T(int t, int pos, int[][] ans) {
        if (pos < 10 + h) {
            if (ans[pos][t + 1] == 0 && ans[pos - 1][t] == 0 && ans[pos - 1][t + 2] == 0)
                add_T(t, pos + 1, ans);
            else {
                ans[pos - 1][t + 1] = ans[pos - 2][t + 1] = ans[pos - 2][t] = ans[pos - 2][t + 2] = 1;
                check_eliminate(pos - 2, ans);
                check_eliminate(pos - 1, ans);
            }
        } else {
            ans[pos - 1][t + 1] = ans[pos - 2][t + 1] = ans[pos - 2][t] = ans[pos - 2][t + 2] = 1;
            check_eliminate(pos - 2, ans);
            check_eliminate(pos - 1, ans);
        }
    }

    public static void add_S(int t, int pos, int[][] ans) {
        if (pos < 10 + h) {
            if (ans[pos][t] == 0 && ans[pos][t + 1] == 0 && ans[pos - 1][t + 2] == 0)
                add_S(t, pos + 1, ans);
            else {
                ans[pos - 1][t] = ans[pos - 1][t + 1] = ans[pos - 2][t + 1] = ans[pos - 2][t + 2] = 1;
                check_eliminate(pos - 2, ans);
                check_eliminate(pos - 1, ans);
            }
        } else {
            ans[pos - 1][t] = ans[pos - 1][t + 1] = ans[pos - 2][t + 1] = ans[pos - 2][t + 2] = 1;
            check_eliminate(pos - 2, ans);
            check_eliminate(pos - 1, ans);
        }
    }

    public static void add_Z(int t, int pos, int[][] ans) {
        if (pos < 10 + h) {
            if (ans[pos][t + 1] == 0 && ans[pos][t + 2] == 0 && ans[pos - 1][t] == 0)
                add_Z(t, pos + 1, ans);
            else {
                ans[pos - 1][t + 1] = ans[pos - 1][t + 2] = ans[pos - 2][t] = ans[pos - 2][t + 1] = 1;
                check_eliminate(pos - 2, ans);
                check_eliminate(pos - 1, ans);
            }
        } else {
            ans[pos - 1][t + 1] = ans[pos - 1][t + 2] = ans[pos - 2][t] = ans[pos - 2][t + 1] = 1;
            check_eliminate(pos - 2, ans);
            check_eliminate(pos - 1, ans);
        }
    }
}

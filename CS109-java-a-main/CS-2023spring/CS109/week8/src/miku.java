import java.util.Scanner;

public class miku {
    public static int tot = 0;
    public static int count = 0;
    public static int m;
    public static int n;
    //
    //200 200
    //a1b2c81d88e90f39738

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        m = input.nextInt();
        n = input.nextInt();
        String s = input.next();
        char[] a = new char[40001];
        char[] f = new char[40001];
        boolean[] flag = new boolean[40001];
        char[][] ans = new char[210][210];
        a = s.toCharArray();
        for (int i = 1; i < a.length; i++) {
            if (!Character.isDigit(a[i])) flag[i] = true;
        }
        int temp = 0;
        int j;
        while (temp < s.length()) {
            for (j = temp + 1; j < s.length(); j++) {
                if (flag[j] == true) break;
            }
            stretch(temp, j - 1, a, f);
            temp = j;
        }
        go_right(m - 1, 0, ans, f);
        for (int i = 0; i < m; i++) {
            for (int l = 0; l < n; l++) {
                System.out.printf("%c", ans[i][l]);
            }
            System.out.println();
        }
    }

    public static void stretch(int left, int right, char[] ini, char[] fin) {
        int sum = 0;
        for (int i = right; i > left; i--) {
            sum += (int) ((int) ini[i] - (int) ('0')) * Math.pow(10.0, right - i);
        }
        for (int i = tot; i < tot + sum; i++) {
            fin[i] = ini[left];
        }
        tot += sum;
    }

    public static void go_right(int i, int j, char[][] ans, char[] f) {
        if (count < tot - 1) {
            ans[i][j] = f[count];
            count++;
            if (j + 1 < n) {
                if (ans[i][j + 1] == '\0') go_right(i, j + 1, ans, f);
                else go_up(i - 1, j, ans, f);
            } else go_up(i - 1, j, ans, f);
        } else if (count == tot - 1) {
            if (ans[i][j] == '\0') ans[i][j] = f[count];
            else ans[i - 1][j - 1] = f[count];
        }
    }

    public static void go_up(int i, int j, char[][] ans, char[] f) {
        if (count < tot - 1) {
            ans[i][j] = f[count];
            count++;
            if (i - 1 >= 0) {
                if (ans[i - 1][j] == '\0') go_up(i - 1, j, ans, f);
                else go_left(i, j - 1, ans, f);
            } else go_left(i, j - 1, ans, f);
        } else if (count == tot - 1) {
            if (ans[i][j] == '\0') ans[i][j] = f[count];
            else ans[i + 1][j - 1] = f[count];
        }
    }

    public static void go_left(int i, int j, char[][] ans, char[] f) {
        if (count < tot - 1) {
            ans[i][j] = f[count];
            count++;
            if (j - 1 >= 0) {
                if (ans[i][j - 1] == '\0') go_left(i, j - 1, ans, f);
                else go_down(i + 1, j, ans, f);
            } else go_down(i + 1, j, ans, f);
        } else if (count == tot - 1) {
            if (ans[i][j] == '\0') ans[i][j] = f[count];
            else ans[i + 1][j + 1] = f[count];
        }
    }

    public static void go_down(int i, int j, char[][] ans, char[] f) {
        if (count < tot - 1) {
            ans[i][j] = f[count];
            count++;
            if (i + 1 < m) {
                if (ans[i + 1][j] == '\0') go_down(i + 1, j, ans, f);
                else go_right(i, j + 1, ans, f);
            } else go_right(i, j + 1, ans, f);
        } else if (count == tot - 1) {
            if (ans[i][j] == '\0') ans[i][j] = f[count];
            else ans[i - 1][j + 1] = f[count];
        }
    }
}
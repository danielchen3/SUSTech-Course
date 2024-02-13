import java.util.Scanner;

public class miku2 {
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

    public static void go_right(int xpos, int ypos, char[][] ans, char[] f) {
        int temp = ypos;
        while (temp >= 0 && temp < n && count <= tot - 1) {
            if (ans[xpos][temp] == '\0') {
                ans[xpos][temp] = f[count];
                temp++;
                count++;
            } else go_up(xpos - 1, temp - 1, ans, f);
        }
        if (temp == n)
            go_up(xpos - 1, temp - 1, ans, f);
    }

    public static void go_up(int xpos, int ypos, char[][] ans, char[] f) {
        int temp = xpos;
        while (temp >= 0 && temp < m && count <= tot - 1) {
            if (ans[temp][ypos] == '\0') {
                ans[temp][ypos] = f[count];
                count++;
                temp--;
            } else go_left(temp + 1, ypos - 1, ans, f);
        }
        if (temp == -1)
            go_left(temp + 1, ypos - 1, ans, f);
    }

    public static void go_left(int xpos, int ypos, char[][] ans, char[] f) {
        int temp = ypos;
        while (temp >= 0 && temp < n && count <= tot - 1) {
            if (ans[xpos][temp] == '\0') {
                ans[xpos][temp] = f[count];
                count++;
                temp--;
            } else go_down(xpos + 1, temp + 1, ans, f);
        }
        if (temp == -1)
            go_down(xpos + 1, temp + 1, ans, f);
    }

    public static void go_down(int xpos, int ypos, char[][] ans, char[] f) {
        int temp = xpos;
        while (temp >= 0 && temp < m && count <= tot - 1) {
            if (ans[temp][ypos] == '\0') {
                ans[temp][ypos] = f[count];
                count++;
                temp++;
            } else go_right(temp - 1, ypos + 1, ans, f);
        }
        if (temp == m)
            go_right(temp - 1, ypos + 1, ans, f);
    }
}
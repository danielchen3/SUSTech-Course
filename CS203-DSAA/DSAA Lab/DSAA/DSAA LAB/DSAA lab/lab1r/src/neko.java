import java.util.Scanner;

public class neko {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        long x1 = input.nextInt();
        long y1 = input.nextInt();
        long x2 = input.nextInt();
        long y2 = input.nextInt();
        long N = input.nextInt();
        String S = input.next();
        char[] s = S.toCharArray();
        if (!if_possible_chase(s, x1, y1, x2, y2)) {
            System.out.println(-1);
        } else {
            long left = 0;
            long right = (long) 1e14;
            while (right > left) {
                long mid = (right + left) / 2;
                long xneko = x2;
                long yneko = y2;
                long div = mid / (long) N;
                long remain = mid % N;
                long addy = 0;
                long addx = 0;
                for (int i = 0; i < N; i++) {
                    if (s[i] == 'D') addy--;
                    else if (s[i] == 'U') addy++;
                    else if (s[i] == 'L') addx--;
                    else if (s[i] == 'R') addx++;
                }
                yneko += (div * addy);
                xneko += (div * addx);
                for (int i = 0; i < remain; i++) {
                    if (s[i] == 'D') yneko--;
                    else if (s[i] == 'U') yneko++;
                    else if (s[i] == 'L') xneko--;
                    else if (s[i] == 'R') xneko++;
                }
                if (manhatten_dis(x1, y1, xneko, yneko) <= mid) right = mid;
                else left = mid + 1;
            }
            if (right == (long) 1e14) System.out.println(-1);
            else System.out.println(right);
        }
    }

    protected static long manhatten_dis(long x1, long y1, long x2, long y2) {
        return Math.abs(y1 - y2) + Math.abs(x1 - x2);
    }


    protected static boolean if_possible_chase(char[] s, long x1, long y1, long x2, long y2) {
        boolean judge = false;
        long init_dis = manhatten_dis(x1, y1, x2, y2);
        for (int i = 0; i < s.length; i++) {
            if (s[i] == 'D') y2--;
            else if (s[i] == 'U') y2++;
            else if (s[i] == 'L') x2--;
            else if (s[i] == 'R') x2++;
            if (manhatten_dis(x1, y1, x2, y2) <= (long) (i + 1)) {
                judge = true;
                break;
            }
        }
        if ((long) s.length > manhatten_dis(x1, y1, x2, y2) - init_dis) judge = true;
        return judge;
    }
}

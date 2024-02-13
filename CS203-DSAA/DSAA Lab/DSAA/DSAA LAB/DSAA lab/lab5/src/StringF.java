import java.io.*;
import java.util.StringTokenizer;

public class StringF {

    public static char[] enc = new char[26];

    public static char[] dec = new char[26];

    public static int[] next = new int[1000001];

    public static void main(String[] args) {
        QReader input = new QReader();
        QWriter output = new QWriter();

        for (int i = 0; i < 26; i++) {
            char x = input.next().charAt(0);
            enc[i] = x;
            dec[(int) x - 97] = (char) (i + 97);
        }
        String s = input.next();

        int n = s.length();
        char[] P = s.toCharArray();
        for (int i = 0; i < n / 2; i++)
            P[i] = dec[(int) P[i] - 97];
        calc_D(P, n);

        int cylc = n - next[n - 1];
        if (n / cylc == 2 && n % cylc == 0) output.println(cylc);
        else if (next[n - 1] == 0) output.println(n);
        else output.println(cylc);

        output.close();
    }

    public static void calc_D(char[] p, int ns) {
        //char[] p = s.toCharArray();
        for (int i = 0; i < ns; i++) {
            next[i] = 0;
        }
        int i = 1, j = 0;
        while (i < ns) {
            if (j < ns && p[i] == p[j]) {
                j++;
                next[i] = j;
                i++;
            } else {
                if (j > 0) {
                    j = next[j - 1];
                } else i++;
            }
        }
    }
}
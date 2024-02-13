import java.io.*;
import java.util.StringTokenizer;

public class Necklace {

    public static int[] next = new int[1000001];

    public static void main(String[] args) {
        QReader input = new QReader();
        QWriter output = new QWriter();

        int T = input.nextInt();

        for (int i = 0; i < T; i++) {
            String s = input.next();

            calc_D(s);

            //循环节长度
            int n = s.length() - next[s.length() - 1];

            if (next[s.length() - 1] != 0 && s.length() % n == 0) output.println(0);

            else output.println(n - s.length() % n);

        }


        output.close();
    }

    public static void calc_D(String s) {
        char[] p = s.toCharArray();
        int ns = s.length();
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
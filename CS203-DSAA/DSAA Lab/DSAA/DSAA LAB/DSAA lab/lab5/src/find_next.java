import java.io.*;
import java.util.StringTokenizer;

public class find_next {

    public static int[] next = new int[10000001];

    public static void main(String[] args) {
        QReader input = new QReader();
        QWriter output = new QWriter();

        String s = input.next();

        calc_D(s);

        for (int i = 0; i < s.length(); i++)
            output.println(next[i]);
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


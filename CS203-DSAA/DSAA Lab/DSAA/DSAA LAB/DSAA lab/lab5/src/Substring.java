import java.io.*;
import java.util.StringTokenizer;

public class Substring {
    public static void main(String[] args) {
        QReader input = new QReader();
        QWriter output = new QWriter();

        int T = input.nextInt();

        for (int i = 0; i < T; i++) {
            String s = input.next();
            long ans = ((long) s.length() * (long) (s.length() + 1)) / (long) 2;
            output.println(ans);
        }

        output.close();
    }
}
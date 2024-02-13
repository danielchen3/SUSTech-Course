import java.io.*;
import java.util.StringTokenizer;

public class leaves {
    public static void main(String[] args) {
        QReader input = new QReader();
        QWriter output = new QWriter();

        boolean[] judge = new boolean[10000001];

        int n = input.nextInt();
        for (int i = 0; i < n - 1; i++) {
            int u = input.nextInt();
            int v = input.nextInt();
            judge[u] = true;
        }

        for (int i = 1; i <= n; i++) {
            if (!judge[i]) {
                output.print(i + " ");
            }
        }
        output.print("\n");

        output.close();
    }
}
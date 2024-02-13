import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Ancestor {
    public static void main(String[] args) {
        QReader input = new QReader();
        QWriter output = new QWriter();
        int T = input.nextInt();
        for (int i = 0; i < T; i++) {
            int n = input.nextInt();
            int m = input.nextInt();
            int[] ancestor = new int[1000001];
            for (int j = 0; j < n - 1; j++) {
                int x = input.nextInt();
                int y = input.nextInt();
                ancestor[x] = y;
            }
            for (int j = 0; j < m; j++) {
                int x = input.nextInt();
                int y = input.nextInt();
                if (x == y) System.out.println("Yes");
                else {
                    int a = x;
                    while (a != 0) {
                        if (a == y) {
                            System.out.println("Yes");
                            break;
                        }
                        a = ancestor[a];
                    }
                    if (a == 0) System.out.println("No");
                }
            }
        }
        output.close();
    }
}

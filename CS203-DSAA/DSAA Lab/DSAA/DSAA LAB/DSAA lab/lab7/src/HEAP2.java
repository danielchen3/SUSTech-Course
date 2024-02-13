import java.io.*;
import java.util.StringTokenizer;

public class HEAP2 {

    public static int[] nodes = new int[1000001];
    public static int size = 0;

    public static void main(String[] args) {
        QReader input = new QReader();
        QWriter output = new QWriter();

        int N = input.nextInt();
        nodes[0] = (int)1e9 + 10;
        for (int i = 0; i < N; i++) {
            int x = input.nextInt();
            put(x);
            int ans = check(size);
            //output.println(nodes[x]);
            output.print(ans + " ");
        }

        output.close();
    }

    public static void put(int val) {
        size++;
        nodes[size] = val;
    }

    public static int check(int pos) {
        int res = 0;
        while (nodes[pos] > nodes[pos / 2]) {
            swap(pos, pos / 2);
            pos = pos / 2;
            res++;
        }
        return res;
    }

    public static void swap(int i, int j) {
        int tmp = nodes[i];
        nodes[i] = nodes[j];
        nodes[j] = tmp;
    }

}

import java.io.*;
import java.util.StringTokenizer;

public class Sum {

    public static int[][] arr = new int[8][8];
    public static boolean[][] chose = new boolean[8][8];

    public static int ans = 0;
    public static int res = 0;

    public static void main(String[] args) {
        QReader input = new QReader();
        QWriter output = new QWriter();

        int T = input.nextInt();

        for (int i = 0; i < T; i++) {
            ans = 0;
            res = 0;
            int N = input.nextInt();
            int M = input.nextInt();
            for (int j = 1; j <= N; j++) {
                for (int k = 1; k <= M; k++) {
                    chose[j][k] = false;
                    arr[j][k] = input.nextInt();
                }
            }
            dfs(1,N,1,M);
            output.println(res);
        }

        output.close();
    }

    public static void dfs(int x, int N, int y, int M) {
        if (!chose[x - 1][y - 1] && !chose[x - 1][y] && !chose[x - 1][y + 1] && !chose[x][y - 1] && !chose[x][y + 1] && !chose[x + 1][y - 1] && !chose[x + 1][y] && !chose[x + 1][y + 1]) {
            ans += arr[x][y];
            res = Math.max(ans, res);
            chose[x][y] = true;
            if (y < M) dfs(x, N, y + 1, M);
            else if (y == M && x < N) dfs(x + 1, N, 1, M);
            chose[x][y] = false;
            ans -= arr[x][y];
            if (y < M) dfs(x, N, y + 1, M);
            else if (y == M && x < N) dfs(x + 1, N, 1, M);
        }
        else{
            if (y < M) dfs(x, N, y + 1, M);
            else if (y == M && x < N) dfs(x + 1, N, 1, M);
        }
    }

}

class QReader {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private StringTokenizer tokenizer = new StringTokenizer("");

    private String innerNextLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    public boolean hasNext() {
        while (!tokenizer.hasMoreTokens()) {
            String nextLine = innerNextLine();
            if (nextLine == null) {
                return false;
            }
            tokenizer = new StringTokenizer(nextLine);
        }
        return true;
    }

    public String nextLine() {
        tokenizer = new StringTokenizer("");
        return innerNextLine();
    }

    public String next() {
        hasNext();
        return tokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public long nextLong() {
        return Long.parseLong(next());
    }
}

class QWriter implements Closeable {
    private BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public void print(Object object) {
        try {
            writer.write(object.toString());
        } catch (IOException e) {
            return;
        }
    }

    public void println(Object object) {
        try {
            writer.write(object.toString());
            writer.write("\n");
        } catch (IOException e) {
            return;
        }
    }

    @Override
    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            return;
        }
    }
}
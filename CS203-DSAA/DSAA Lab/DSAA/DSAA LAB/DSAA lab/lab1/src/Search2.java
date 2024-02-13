import java.util.Scanner;


import java.io.*;
import java.util.*;

public class Search2 {
    public static void main(String[] args) {
        //sample
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        boolean[] jud = new boolean[1000001];
        int[] a = new int[1000001];
        for (int i = 0; i < n; i++)
            a[i] = in.nextInt();
        int[] b = new int[1000001];
        int T = in.nextInt();
        for (int i = 0; i < T; i++)
            b[i] = in.nextInt();
        int[] cnt = new int[100001];
        for (int i = 0; i < n; i++) {
            cnt[a[i]]++;
        }
        for (int j = 0; j < T; j++) {
            if (cnt[b[j]] == 0) jud[j] = false;
            else jud[j] = true;
        }
        for (int i = 0; i < T; i++) {
            if (jud[i]) out.print("yes\n");
            else out.print("no\n");
        }
        out.close();
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
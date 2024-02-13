import java.io.*;
import java.util.StringTokenizer;

public class FSA {

    public static int[] trans = new int[26];

    public static int[] next = new int[10000001];

    public static boolean[] judge = new boolean[26];

    public static void main(String[] args) {
        QReader input = new QReader();
        QWriter output = new QWriter();

        String s = input.next();

        calc_D(s);


        for (int i = 0; i < s.length(); i++) {
            //empty trans
            for (int j = 0; j < 26; j++) {
                trans[j] = 0;
                judge[j] = false;
            }

            trans[s.charAt(i) - 'a'] = i + 1;
            judge[s.charAt(i) - 'a'] = true;

            if (i != 0) {
                for (int j = 0; j < 26; j++) {
                    if (!judge[j]) {
                        int x = i - 1;
                        int y = next[i - 1];
                        while (x < i) {
                            if (s.charAt(y) == (char) (j + 97)) {
                                trans[j] = y + 1;
                                x++;
                            } else {
                                if (y > 0) y = next[y - 1];
                                else x++;
                            }
                        }
                    }
                }
            }

            //输出trans
            for (int j = 0; j < 26; j++) {
                if (j != 25) output.print(trans[j] + " ");
                else output.println(trans[j]);
            }

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

import java.io.*;
import java.util.StringTokenizer;

public class Stocks {
    public static void main(String[] args) {
        QReader input = new QReader();
        QWriter output = new QWriter();

        int T = input.nextInt();

        for (int i = 0; i < T; i++) {
            int[] cnt = new int[1000001];
            int n = input.nextInt();
            //int[] stocks = new int[n];
            Stack stack = new Stack();
            for (int j = 0; j < n; j++) {
                int x = input.nextInt();
                while (!stack.isEmpty() && x > stack.peek().val) {
                    cnt[stack.peek().index] = j - stack.peek().index;
                    stack.pop();
                }
                if (stack.isEmpty() || x <= stack.peek().val) {
                    Stockss stockss = new Stockss();
                    stockss.index = j;
                    stockss.val = x;
                    stack.push(stockss);
                }
            }
            int q = input.nextInt();
            for (int j = 0; j < q; j++) {
                int x = input.nextInt();
                if (cnt[x - 1] == 0) output.println(-1);
                else output.println(cnt[x - 1]);
            }

        }

        output.close();
    }
}


class Stack {
    public int maxlength = 1000001;

    public Stockss[] s = new Stockss[1000001];

    int index = -1;

    public void push(Stockss stockss) {
        if (index == maxlength) return;

        index++;
        s[index] = stockss;
    }

    public int pop() {
        int ans = 0;
        if (index == -1) ans = -1;
        else {
            ans = s[index].val;
            index--;
        }
        return ans;
    }

    public Stockss peek() {
        if (index == -1) return null;
        return s[index];
    }

    public boolean isEmpty() {
        return index == -1;
    }

    public boolean isFull() {
        return index == maxlength;
    }

    public void clear() {
        for (int i = 0; i < index; i++) {
            s[i] = null;
        }
        index = 0;
    }

    public int getsize() {
        return index;
    }

}

class Stockss {
    int index = 0;
    int val = 0;
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

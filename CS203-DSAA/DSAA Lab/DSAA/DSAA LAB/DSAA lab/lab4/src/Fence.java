import java.io.*;
import java.util.StringTokenizer;

public class Fence {
    public static void main(String[] args) {
        QReader input = new QReader();
        QWriter output = new QWriter();

        int k = input.nextInt();
        int n = input.nextInt();
        int[] arr = new int[10000001];

        int ans = 0;
        for (int i = 0; i < n; i++) arr[i] = input.nextInt();

        int left = 0;
        int right = 0;

        long min = (long) 3e9;
        int max = 0;

        while (right < n) {
            max = Math.max(max, arr[right]);
            min = Math.min(min, arr[right]);

            if (max - min > k) {
                ans = Math.max(ans, right - left);
                left = right;

                min = (long) 3e9;
                max = 0;

                //从当前右边往前找，找到一个left
                while (left >= 0 && Math.abs(arr[right] - arr[left]) <= k) {
                    max = Math.max(max, arr[left]);
                    min = Math.min(min, arr[left]);
                    left--;
                }
                left++;
            }

            ans = Math.max(ans, right - left + 1);
            right++;

        }

        output.println(ans);
        output.close();
    }
}

class Fence_queue {
    int rear = 0;
    int front = 0;

    int maxlength = 100000001;

    int[] s = new int[100000001];

    public void enqueue(int val) {
        if (rear == maxlength) {
            return;
        }
        s[rear] = val;
        rear++;
    }

    public int dequeue() {
        int ans = -1;
        if (!isEmpty()) {
            ans = s[front];
            s[front] = 0;
            front++;
        }
        return ans;
    }

    public int dequeuefromrear() {
        int ans = -1;
        if (!isEmpty()) {
            ans = s[rear];
            s[rear] = 0;
            rear--;
        }
        return ans;
    }

    public int seekrear() {
        return s[rear];
    }

    public int seek() {
        return s[front];
    }


    public boolean isEmpty() {
        return front > rear;
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


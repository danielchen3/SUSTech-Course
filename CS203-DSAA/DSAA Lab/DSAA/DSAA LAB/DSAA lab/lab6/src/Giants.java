import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Giants {

    public static ttreenode[] city = new ttreenode[1000001];

    public static int[] ss = new int[1000001];

    public static int[] temp = new int[1000001];

    public static void main(String[] args) {
        QReader input = new QReader();
        QWriter output = new QWriter();


        int n = input.nextInt();

        for (int i = 1; i <= n; i++) city[i] = new ttreenode();

        for (int i = 0; i < n - 1; i++) {
            int u = input.nextInt();
            int v = input.nextInt();
            city[u].val = u;
            city[v].val = v;
            city[u].children.add(city[v]);
            city[v].children.add(city[u]);
        }

        int m = input.nextInt();

        for (int i = 0; i < m; i++) {
            int x = input.nextInt();
            city[x].isoccupied = true;
        }

        city[1].depth = 0;
        city[1].isvisited = true;
        int[] arr = new int[1000001];
        int flag = 0;
        arr[0] = -2;
        //分根节点的各个子节点遍历树并记录深度
        for (int i = 0; i < city[1].children.size(); i++) {
            int temp = 1;
            Queueueue queue2 = new Queueueue();
            city[1].children.get(i).depth = 1;
            queue2.enqueue(city[1].children.get(i).val);
            while (!queue2.isEmpty()) {
                int index = queue2.dequeue();
                if (!city[index].isvisited) {
                    for (int j = 0; j < city[index].children.size(); j++) {
                        if (!city[index].children.get(j).isvisited)
                            city[index].children.get(j).depth = city[index].depth + 1;
                    }
                    for (int j = 0; j < city[index].children.size(); j++) {
                        if (!city[index].children.get(j).isvisited) queue2.enqueue(city[index].children.get(j).val);
                    }
                    city[index].isvisited = true;
                    if (city[index].isoccupied) {
                        arr[temp] = Math.max(arr[temp - 1] + 1, city[index].depth);
                        temp++;
                    }
                }
            }
            //for(int j = 0; j < temp; j++) output.println(arr[j]);
            ss[flag++] = arr[temp - 1];
        }

        MergeSort(0, flag - 1);

        //output.println(flag);
        //for (int i = 1; i <= n; i++) output.println(city[i].depth);
        //for (int i = 0; i < flag; i++) output.println(ss[flag]);

        output.println(ss[flag - 1]);

        output.close();
    }

    protected static void MergeSort(int left, int right) {
        if (left >= right) return;
        int mid = (left + right) / 2;
        MergeSort(left, mid);
        MergeSort(mid + 1, right);
        merge(left, mid, right);
    }

    protected static void merge(int left, int mid, int right) {
        int leftpos = left;
        int rightpos = mid + 1;
        int temppos = left;

        //分成两组，并且按照大小顺序加入新的数组
        while (leftpos <= mid && rightpos <= right) {
            if (ss[leftpos] < ss[rightpos]) temp[temppos++] = ss[leftpos++];
            else temp[temppos++] = ss[rightpos++];
        }

        //把还没加进去的数字加进去
        while (leftpos <= mid) {
            temp[temppos++] = ss[leftpos++];
        }
        while (rightpos <= right) {
            temp[temppos++] = ss[rightpos++];
        }
        for (int i = left; i <= right; i++)
            ss[i] = temp[i];
    }
}


class ttreenode {
    int val;

    ArrayList<ttreenode> children = new ArrayList<ttreenode>();

    boolean isvisited = false;

    boolean isoccupied = false;

    int depth;

    int beep;

}

class Queueueue {
    int rear = 0;
    int front = 0;

    int maxlength = 5000001;

    int[] s = new int[5000001];

    public void enqueue(int val) {
        if (rear == maxlength) {
            //System.out.println("Queue is full!");
            return;
        }
        s[rear] = val;
        rear++;
    }

    public int dequeue() {
        int newnode = s[front];
        if (front < rear) {
            s[front] = 0;
            front++;
        }
        return newnode;
    }

    public boolean isEmpty() {
        return front == rear;
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

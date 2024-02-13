import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Simple {

    public static Node[] nodes = new Node[100001];

    public static Node[] real_nodes = new Node[100001];

    public static int[] fake_sort = new int[100001];

    public static int[] fake_real = new int[100001];

    public static int size = 0;

    public static int cnt = 0;

    public static int[] size_ssc = new int[100001];

    public static int cnt_grp = 0;

    public static void main(String[] args) {
        QReader input = new QReader();
        QWriter output = new QWriter();


        int n = input.nextInt();
        int m = input.nextInt();
        int S = input.nextInt();


        for (int i = 0; i <= n; i++) {
            nodes[i] = new Node();
            real_nodes[i] = new Node();
            nodes[i].index = i;
            real_nodes[i].index = i;
        }

        for (int i = 0; i < m; i++) {
            int u = input.nextInt();
            int v = input.nextInt();
            nodes[v].children.add(nodes[u]);
            nodes[v].size++;
            real_nodes[u].children.add(real_nodes[v]);
            real_nodes[u].size++;
        }


        for (int i = 1; i <= n; i++) {
            if (!nodes[i].isvisited)
                dfs(nodes[i]);
        }

        //for (int i = 0; i < n; i++) output.print(fake_sort[i] + " ");


        for (int i = size - 1; i >= 0; i--) {
            if (!real_nodes[fake_sort[i]].isvisited) {
                dfs_real(real_nodes[fake_sort[i]]);
                size_ssc[cnt_grp++] = cnt;
            }
        }

        Node[] arr = new Node[100001];

        for (int i = 0; i < cnt_grp; i++) {
            arr[i] = new Node();
            arr[i].index = i;
        }

        //output.println(cnt_grp);

        //添加连通图的联通关系
        for (int i = 0; i < size_ssc[0]; i++) {
            for (int j = 0; j < real_nodes[fake_real[i]].children.size(); j++) {
                if (real_nodes[fake_real[i]].children.get(j).SSC_GRP != real_nodes[fake_real[i]].SSC_GRP) {
                    if (real_nodes[fake_real[i]].children.get(j).SSC_GRP != real_nodes[fake_real[i]].SSC_GRP)
                        arr[real_nodes[fake_real[i]].children.get(j).SSC_GRP].innerpath++;
                }
            }
        }
        for (int j = 1; j < cnt_grp; j++) {
            for (int i = size_ssc[j - 1]; i < size_ssc[j]; i++) {
                for (int k = 0; k < real_nodes[fake_real[i]].children.size(); k++) {
                    if (real_nodes[fake_real[i]].children.get(k).SSC_GRP != real_nodes[fake_real[i]].SSC_GRP)
                        arr[real_nodes[fake_real[i]].children.get(k).SSC_GRP].innerpath++;
                }
            }
        }


        int ans = 0;
        for (int i = 0; i < cnt_grp; i++) {
            if (arr[i].innerpath == 0) ans++;
        }


        int index = real_nodes[S].SSC_GRP;

        if (arr[index].innerpath == 0)
            output.println(ans - 1);
        else output.println(ans);

//        for (int i = 0; i < cnt_grp; i++) output.print(size_ssc[i] + " ");
//        output.print("\n");
//
//        for (int i = 0; i < n; i++) output.print(fake_real[i] + " ");
//
//        output.println(size);


        output.close();
    }

    public static void dfs(Node node) {
        if (!node.isvisited && node.size > 0) {
            node.isvisited = true;
            boolean hasnosons = true;
            for (int i = 0; i < node.children.size(); i++) {
                if (node.children.get(i).isvisited) node.size--;
                else {
                    hasnosons = false;
                    node.size--;
                    dfs(node.children.get(i));
                    node.isvisited = true;
                }
            }
            if (hasnosons || node.size == 0)
                fake_sort[size++] = node.index;
        } else {
            node.isvisited = true;
            fake_sort[size++] = node.index;
        }
    }

    public static void dfs_real(Node node) {
        if (!node.isvisited && node.size > 0) {
            node.isvisited = true;
            boolean hasnosons = true;
            for (int i = 0; i < node.children.size(); i++) {
                if (node.children.get(i).isvisited) node.size--;
                else {
                    hasnosons = false;
                    node.size--;
                    dfs_real(node.children.get(i));
                    node.isvisited = true;
                }
            }
            if (hasnosons || node.size == 0) {
                fake_real[cnt++] = node.index;
                node.SSC_GRP = cnt_grp;
            }

        } else {
            node.isvisited = true;
            fake_real[cnt++] = node.index;
            node.SSC_GRP = cnt_grp;
        }
    }

}

class Node {
    ArrayList<Node> children = new ArrayList<>();

    int size;

    boolean isvisited = false;

    int index;

    int SSC_GRP;


    int innerpath = 0;
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

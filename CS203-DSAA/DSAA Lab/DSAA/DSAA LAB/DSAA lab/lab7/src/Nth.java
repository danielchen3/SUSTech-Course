import java.io.*;
import java.util.StringTokenizer;

public class Nth {

    public static TTreenode root;

    public static int num = 0;

    public static TTreenode[] nodes = new TTreenode[1000001];

    public static void main(String[] args) {
        QReader input = new QReader();
        QWriter output = new QWriter();

        int m = input.nextInt();
        int k = input.nextInt();

        for (int i = 0; i < m; i++) {
            int x = input.nextInt();
            nodes[i] = new TTreenode(x);
            insert(nodes[i]);
        }

        for (int i = 0; i < m - k + 1; i++) {
            int x = input.nextInt();
            output.println(traverse(root, x));
            delete(nodes[i]);
        }

        output.close();
    }

    public static void insert(TTreenode treenode) {
        TTreenode cur;
        if (root == null) {
            root = treenode;
            return;
        } else {
            cur = root;
            while (true) {
                if (treenode.val >= cur.val) {
                    if (cur.right == null) {
                        cur.right = treenode;
                        return;
                    } else cur = cur.right;
                } else {
                    if (cur.left == null) {
                        cur.left = treenode;
                        return;
                    } else cur = cur.left;
                }
            }
        }
    }

    public static void delete(TTreenode treenode) {
        //寻找到这个要删除的
        TTreenode tmp = root;

        TTreenode father = root;
        while (tmp != null && treenode != tmp) {
            father = tmp;
            if (treenode.val > tmp.val) {
                tmp = tmp.right;
            } else tmp = tmp.left;
        }

        if (tmp == null) return;
        //叶子结点
        if (tmp.left == null && tmp.right == null) {
            if (father.left == tmp) father.left = null;
            if (father.right == tmp) father.right = null;
        }

        //只有一个子节点
        else if (tmp.left == null) {
            if (father.left == tmp) father.left = tmp.right;
            else father.right = tmp.right;
        } else if (tmp.right == null) {
            if (father.left == tmp) father.left = tmp.left;
            else father.right = tmp.left;
        }

        //两边都有结点
        //先找到这个结点中序遍历后面一个结点
        else {
            father = tmp;
            TTreenode tmp_right = tmp.right;
            while (tmp_right.left != null) {
                father = tmp_right;
                tmp_right = tmp_right.left;
            }

            //找到之后进行交换
            TTreenode tt = tmp;
            tmp = tmp_right;
            tmp_right = tt;

            if (father.left == tmp_right) father.left = tmp_right.right;
            else father.right = tmp_right.right;
        }

    }

    public static TTreenode traverse(TTreenode root, int m) {
        if (root != null) {
            traverse(root.left, m);
            num++;
            if (num == m) return root;
            traverse(root.right, m);
        }
        return null;
    }


    public static TTreenode Countunbalancednode(TTreenode treenode) {
        TTreenode temp = treenode.father;
        //准备旋转父节点
        if (temp != null) {
            int abs_bf = Math.abs(temp.bf);
            if (abs_bf > 1) {
                return temp;
            } else return Countunbalancednode(temp);
        } else {
            if (Math.abs(treenode.bf) > 1) return treenode;
        }
        return null;
    }




}

class TTreenode {
    int val;
    TTreenode left;
    TTreenode right;

    TTreenode father;

    int bf = 0;

    public TTreenode(int val) {
        this.val = val;
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

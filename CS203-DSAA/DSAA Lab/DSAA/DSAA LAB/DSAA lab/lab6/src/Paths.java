import java.io.*;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Paths {

    public static long ans = 0;

    public static Node[] nodes = new Node[5000001];

    public static void main(String[] args) {
        QReader input = new QReader();
        QWriter output = new QWriter();

        int n = input.nextInt();
        long num = input.nextInt();
        long ans = 0;


        for (int i = 1; i <= n; i++) {
            nodes[i] = new Node();
        }

        for (int i = 0; i < n - 1; i++) {
            int u = input.nextInt();
            int v = input.nextInt();
            long w = input.nextInt();
            nodes[u].index = u;
            nodes[v].index = v;
            nodes[u].children.add(nodes[v]);
            nodes[v].children.add(nodes[u]);
            nodes[u].weight.add(w);
            nodes[v].weight.add(w);
        }

        Queueue queue1 = new Queueue();
        queue1.enqueue(1);

        while (!queue1.isEmpty()) {
            int index = queue1.dequeue();
            if (!nodes[index].isvisited) {
                for (int j = 0; j < nodes[index].children.size(); j++) {
                    if (!nodes[index].children.get(j).isvisited) {
                        nodes[index].children.get(j).path = nodes[index].path + nodes[index].weight.get(j);
                    }
                }
                //儿子都入队
                for (int j = 0; j < nodes[index].children.size(); j++) {
                    if (!nodes[index].children.get(j).isvisited) queue1.enqueue(nodes[index].children.get(j).index);
                }
                nodes[index].isvisited = true;
            }
        }


        for (int i = 2; i <= n; i++) {
            if (nodes[i].children.size() == 1 && nodes[i].path == num) ans++;
        }

        output.println(ans);
        output.close();
    }

//    public static void process_path(Node node) {
//
//        if (node.children.size() == 1 && !node.ishead) return;
//
//        for (int j = 0; j < node.children.size(); j++) {
//            if (!node.children.get(j).isvisited) node.children.get(j).path = node.path + node.weight.get(j);
//        }
//        for (int j = 0; j < node.children.size(); j++)
//            process_path(node.children.get(j));
//    }

}

class Queueue {
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

class Node {
    long path = 0;
    ArrayList<Node> children = new ArrayList<>();
    ArrayList<Long> weight = new ArrayList<>();
    int index;
    boolean isvisited = false;

    boolean ishead = false;
}



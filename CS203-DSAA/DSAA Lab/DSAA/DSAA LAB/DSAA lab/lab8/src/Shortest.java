import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Shortest {

    public static Node[] nodes = new Node[5000001];

    public static void main(String[] args) {
        QReader input = new QReader();
        QWriter output = new QWriter();
        int T = input.nextInt();
        for (int i = 0; i < T; i++) {

            int n = input.nextInt();
            int m = input.nextInt();
            int x = input.nextInt();

            for (int j = 1; j <= n; j++) nodes[j] = new Node();

            for (int j = 0; j < m; j++) {
                int u = input.nextInt();
                int v = input.nextInt();
                nodes[u].index = u;
                nodes[v].index = v;
                nodes[u].children.add(nodes[v]);
                nodes[v].children.add(nodes[u]);
                nodes[u].weight.add((long) 1);
                nodes[v].weight.add((long) 1);
            }

            Queueue queue1 = new Queueue();
            queue1.enqueue(x);

            while (!queue1.isEmpty()) {
                int index = queue1.dequeue();

                for (int j = 0; j < nodes[index].children.size(); j++) {
                    if (!nodes[index].children.get(j).isvisited) {
                        nodes[index].children.get(j).path = nodes[index].path + nodes[index].weight.get(j);
                    }
                }
                //儿子都入队
                for (int j = 0; j < nodes[index].children.size(); j++) {
                    if (!nodes[index].children.get(j).isvisited) {
                        queue1.enqueue(nodes[index].children.get(j).index);
                        nodes[index].children.get(j).isvisited = true;
                    }
                }
                nodes[index].isvisited = true;
            }

            for (int j = 1; j <= n; j++) {
                if (j == x) output.print(0 + " ");
                else if (nodes[j].path == 0) output.print(-1 + " ");
                else output.print(nodes[j].path + " ");
            }

            output.print("\n");
        }
        output.close();
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

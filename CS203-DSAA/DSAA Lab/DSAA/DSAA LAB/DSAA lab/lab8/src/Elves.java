import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Elves {
    public static void main(String[] args) {
        QReader input = new QReader();
        QWriter output = new QWriter();

        DAG_node[] dags = new DAG_node[1000001];
        int T = input.nextInt();
        for (int i = 0; i < T; i++) {
            Queueue queue1 = new Queueue();
            int n = input.nextInt();
            int m = input.nextInt();
            for (int j = 1; j <= n; j++) {
                dags[j] = new DAG_node();
                long a = input.nextInt();
                long b = input.nextInt();
                dags[j].vala = a;
                dags[j].valb = b;
                dags[j].index = j;
            }
            //添加结点并标记父子关系,计算入度
            for (int j = 1; j <= m; j++) {
                int u = input.nextInt();
                int v = input.nextInt();
                dags[u].children.add(dags[v]);
                dags[v].inner_path++;
            }

            for (int j = 1; j <= n; j++) {
                if (dags[j].inner_path == 0) {
                    queue1.enqueue(j);
                }
            }

            while (!queue1.isEmpty()) {
                int index = queue1.dequeue();
                //入度为0的儿子入队,更新F_value
                for (int j = 0; j < dags[index].children.size(); j++) {
                    dags[index].children.get(j).F_value = (dags[index].children.get(j).F_value + (dags[index].F_value + dags[index].vala) % 1000000007) % 1000000007;
                    if (dags[index].children.get(j).inner_path != 0) {
                        dags[index].children.get(j).inner_path--;
                    }
                    if (dags[index].children.get(j).inner_path == 0) {
                        queue1.enqueue(dags[index].children.get(j).index);
                    }
                }
            }


//            int[] Toposort = new int[n];
//            int cnt = 0;
//            while (!queue1.isEmpty()) {
//                int index = queue1.dequeue();
//                Toposort[cnt++] = index;
//                //入度为0的儿子入队,更新F_value
//                for (int j = 0; j < dags[index].children.size(); j++) {
//                    if (dags[index].children.get(j).inner_path != 0) {
//                        dags[index].children.get(j).inner_path--;
//                    }
//                    if (dags[index].children.get(j).inner_path == 0) {
//                        queue1.enqueue(dags[index].children.get(j).index);
//                    }
//                }
//            }
//
//            for (int j = 0; j < n; j++) {
//                for (int k = 0; k < dags[Toposort[j]].children.size(); k++) {
//                    dags[Toposort[j]].children.get(k).F_value += (dags[Toposort[j]].F_value + dags[Toposort[j]].vala) % 1000000007;
//                }
//            }

            long ans = 0;

            for (int j = 1; j <= n; j++) {
                ans = (ans + (dags[j].F_value * dags[j].valb) % 1000000007) % 1000000007;
            }


            output.println(ans);
        }

        output.close();
    }
}

class DAG_node {


    long F_value;

    ArrayList<DAG_node> children = new ArrayList<>();
    int index;
    boolean isvisited = false;

    long vala, valb;

    int have_path = 0;

    int inner_path = 0;
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



import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Tower {

    public static Def[] defs = new Def[1000001];


    public static void main(String[] args) {
        QReader input = new QReader();
        QWriter output = new QWriter();

        int T = input.nextInt();
        Queueueue queue1 = new Queueueue();


        for (int i = 0; i < T; i++) {
            queue1.empty_queue();
            int ans = 0;
            int n = input.nextInt();
            int m = input.nextInt();
            for (int j = 1; j <= n; j++) {
                defs[j] = new Def();
            }
            for (int j = 0; j < m; j++) {
                int x = input.nextInt();
                int y = input.nextInt();
                defs[x].index = x;
                defs[y].index = y;
                defs[x].children.add(defs[y]);
                defs[y].children.add(defs[x]);
            }


            queue1.enqueue(1);
            defs[1].isvisited = true;
            defs[1].hasTower = true;
            ans++;
            while (!queue1.isEmpty()) {
                int index = queue1.dequeue();
                if (!defs[index].hasTower) {
                    for (int j = 0; j < defs[index].children.size(); j++) {
                        if (!defs[index].children.get(j).isvisited) {
                                queue1.enqueue(defs[index].children.get(j).index);
                            defs[index].children.get(j).hasTower = true;
                            ans++;
                            defs[index].children.get(j).isvisited = true;
                        }
                    }
                } else {
                    for (int j = 0; j < defs[index].children.size(); j++) {
                        if (!defs[index].children.get(j).isvisited) {
                            queue1.enqueue(defs[index].children.get(j).index);
                            defs[index].children.get(j).isvisited = true;
                        }
                    }
                }
            }
            //判断奇数层还是偶数层
            if (ans <= n / 2) {
                output.println(ans);
                for (int j = 1; j <= n; j++) {
                    if (defs[j].hasTower)
                        output.print(j + " ");
                }
            } else {
                output.println(n - ans);
                for (int j = 1; j <= n; j++) {
                    if (!defs[j].hasTower)
                        output.print(j + " ");
                }
            }
            output.print("\n");
        }
        output.close();
    }
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

    public void empty_queue(){
        rear = 0;
        front = 0;
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

class Def {
    int nei;
    ArrayList<Def> children = new ArrayList<>();
    int index;
    boolean isvisited = false;
    boolean hasTower = false;
}
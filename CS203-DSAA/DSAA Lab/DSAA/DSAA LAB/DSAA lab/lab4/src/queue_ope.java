import java.io.*;
import java.util.StringTokenizer;

public class queue_ope {
    public static void main(String[] args) {
        QReader input = new QReader();
        QWriter output = new QWriter();

        int n = input.nextInt();

        queueue queue1 = new queueue();

        for (int i = 0; i < n; i++) {
            char c = input.next().charAt(0);

            int x = input.nextInt();

            if (c == 'E') {
                queue1.enqueue(x);
            }
            if (c == 'D') {
                for (int j = 0; j < x; j++) {
                    queue1.dequeue();
                }
                output.println(queue1.s[queue1.front]);
            }

        }

        output.close();

    }
}

class queueue {
    int rear = 0;
    int front = 0;

    int maxlength = 1000001;

    int[] s = new int[1000001];

    public void enqueue(int val) {
        if (rear == maxlength) {
            return;
        }
        s[rear] = val;
        rear++;
    }

    public void dequeue() {
        if (front < rear) {
            s[front] = 0;
            front++;
        }
    }
}

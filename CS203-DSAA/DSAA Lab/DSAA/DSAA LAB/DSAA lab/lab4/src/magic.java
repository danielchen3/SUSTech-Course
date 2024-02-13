import java.io.*;
import java.util.StringTokenizer;

public class magic {
    public static void main(String[] args) {
        QReader input = new QReader();
        QWriter output = new QWriter();

        int m = input.nextInt();
        Queueueue q1 = new Queueueue();

        long ans = 0;

        int[] ori = new int[30000001];

        int cnt = 0;
        while (true) {
            int x = input.nextInt();
            if (x == -1) break;
            else
                ori[cnt++] = x;
        }

        for (int i = 0; i < m; i++) {
            if (i == 0) {
                q1.enqueue(ori[i]);
            } else {
                while (q1.rear - 1 >= 0 && ori[i] > q1.s[q1.rear - 1]) {
                    q1.rear--;
                    q1.dequeuefromrear();
                    q1.rear++;
                }
                q1.enqueue(ori[i]);
            }
        }

        ans = q1.seek();

        int tag = 0;
        for (int i = m; i < cnt; i++) {
            //滑动窗口后出去的一个刚好是前一个最大值，把最大值移出去，然后加入新的元素
            //output.println(q1.seek());
            if (i != m) ans ^= q1.seek();
            if (q1.seek() == ori[tag]) {
                q1.dequeue();
            }
            tag++;
            if (ori[i] <= q1.seekrear()) {
                q1.rear++;
                q1.enqueue(ori[i]);
                q1.rear--;
            } else {
                boolean judge = false;
                while (ori[i] > q1.seekrear()) {
                    q1.dequeuefromrear();
                    judge = true;
                    if (q1.isEmpty()) break;
                }
                if (judge) {
                    q1.rear++;
                    q1.enqueue(ori[i]);
                    q1.rear--;
                }
            }
        }

        //output.println(q1.seek());
        ans ^= q1.seek();

        output.println(ans);
        output.close();
    }
}

class Queueueue {
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

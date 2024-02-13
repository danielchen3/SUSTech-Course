import java.io.*;
import java.util.StringTokenizer;

public class spider {
    public static void main(String[] args) {
        QReader input = new QReader();
        QWriter output = new QWriter();
        int T = input.nextInt();

        for (int i = 0; i < T; i++) {
            int n = input.nextInt();
            int[] ans = new int[1000001];
            boolean[] isexist = new boolean[1000001];
            Slot slot1 = new Slot();


            for (int j = 1; j <= n; j++) isexist[j] = true;
            int point = 0;
            int cnt = 1;
            boolean sb = false;
            for (int j = 0; j < n; j++) {
                int x = input.nextInt();


                if (j == n - 1) sb = true;

                if (x != 1) {
                    slot1.push(x);
                    isexist[x] = false;
                }

                if (x == 1) {
                    point = j;
                    isexist[1] = false;
                    ans[0] = 1;
                    break;
                }
            }


            int dd = n;
            for (int j = 2; j <= n; j++) {
                if (isexist[j]) {
                    dd = j;
                    break;
                }
            }

            while (!sb) {
                for (int j = point + 1; j < n; j++) {
                    int x = input.nextInt();
                    if (j == n - 1) sb = true;
                    while (slot1.peek() != 0 && slot1.peek() < dd) {
                        ans[cnt++] = slot1.pop();
                    }
                    if (x != dd) {
                        slot1.push(x);
                        isexist[x] = false;
                    }
                    if (x == dd) {
                        point = j;
                        isexist[dd] = false;
                        ans[cnt++] = dd;
                        //tag = dd + 1;
                        break;
                    }
                }
                for (int j = dd + 1; j <= n; j++) {
                    if (isexist[j]) {
                        dd = j;
                        break;
                    }
                }
            }

            for (int j = cnt; j < n; j++) {
                ans[j] = slot1.pop();
            }

            for (int j = 0; j < n; j++) {
                if (j != n - 1) output.print(ans[j] + " ");
                else output.println(ans[j]);
            }
        }


        output.close();
    }
}

class Slot {
    public int maxlength = 1000001;

    public int[] s = new int[1000001];

    int index = -1;

    public void push(int val) {
        if (index == maxlength) return;

        index++;
        s[index] = val;
    }

    public int pop() {
        int ans = 0;
        if (index == -1) ans = 0;
        else {
            ans = s[index];
            index--;
        }
        return ans;
    }

    public int peek() {
        if (index == -1) return 0;
        return s[index];
    }

    public boolean isEmpty() {
        return index == 0;
    }

    public boolean isFull() {
        return index == maxlength;
    }

    public void clear() {
        for (int i = 0; i < index; i++) {
            s[i] = 0;
        }
        index = 0;
    }

    public int getsize() {
        return index;
    }

}
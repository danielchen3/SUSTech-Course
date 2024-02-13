import java.io.*;
import java.util.StringTokenizer;

public class Playroom {
    public static void main(String[] args) {
        QReader input = new QReader();
        QWriter output = new QWriter();
        String s = input.next();

        char[] ss = s.toCharArray();
        char[] tt = new char[1000001];

        int num = 0;
        for (int i = 0; i < ss.length; i++) {
            if (ss[i] == '(' && ss[i + 1] == ')') {
                tt[num++] = '1';
                i++;
                if (i == s.length() - 1) break;
            } else tt[num++] = ss[i];
        }

        Count count = new Count();

        for (int i = 0; i < num; i++) {
            if (tt[i] == '(') {
                count.push(514330);
            } else if (tt[i] == '1') {
                count.push(1);
            } else {
                long dd = 0;
                while (count.peek() != 514330) {
                    dd += count.pop();
                }
                count.pop();
                count.push((int) ((dd * (long) 2) % (long) 514329));
            }
        }

        long res = 0;

        while (count.peek() != 0)
            res += (long) count.pop();
        output.println(res % 514329);

        output.close();
    }
}

class Play {
    public int maxlength = 1000001;

    public char[] s = new char[1000001];

    int index = -1;

    public void push(char val) {
        if (index == maxlength) return;

        index++;

        s[index] = val;
    }

    public char pop() {
        char ans = '\0';
        if (index != -1) {
            ans = s[index];
            index--;
        }
        return ans;
    }

    public char peek() {
        if (index == -1) return '\0';
        return s[index];
    }

    public boolean isEmpty() {
        return index == -1;
    }

    public boolean isFull() {
        return index == maxlength;
    }

    public void clear() {
        for (int i = 0; i < index; i++) {
            s[i] = '\0';
        }
        index = 0;
    }

    public int getsize() {
        return index;
    }

}

class Count {
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
        if (index != -1) {
            ans = s[index];
            s[index] = 0;
            index--;
        }
        return ans;
    }

    public int peek() {
        if (index == -1) return 0;
        return s[index];
    }

    public boolean isEmpty() {
        return index == -1;
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


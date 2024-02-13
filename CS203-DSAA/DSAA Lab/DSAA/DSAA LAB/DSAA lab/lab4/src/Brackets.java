import java.io.*;
import java.util.StringTokenizer;

public class Brackets {
    public static void main(String[] args) {
        QReader input = new QReader();
        QWriter output = new QWriter();

        int T = input.nextInt();

        for (int i = 0; i < T; i++) {
            bracket_stack stack1 = new bracket_stack();
            int n = input.nextInt();
            String s = input.next();

            char[] ss = s.toCharArray();
            boolean ans = false;


            boolean judge = true;

            for (int j = 0; j < n; j++) {
                if (ss[j] == '[' || ss[j] == '{' || ss[j] == '(') {
                    stack1.push(ss[j]);
                } else {
                    if ((ss[j] == ']' && stack1.peek() != '[') || (ss[j] == '}' && stack1.peek() != '{') || (ss[j] == ')' && stack1.peek() != '(')) {
                        judge = false;
                        break;
                    } else if ((ss[j] == ']' && stack1.peek() == '[') || (ss[j] == '}' && stack1.peek() == '{') || (ss[j] == ')' && stack1.peek() == '(')) {
                        stack1.pop();
                    }
                }
            }

            if (judge && stack1.isEmpty()) ans = true;

            if (ans) output.println("YES");
            else output.println("NO");
        }
        output.close();
    }
}


class bracket_stack {
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

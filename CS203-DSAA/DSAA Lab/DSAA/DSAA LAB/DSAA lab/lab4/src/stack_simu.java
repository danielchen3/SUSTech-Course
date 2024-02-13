public class stack_simu {
    public static void main(String[] args) {

    }
}

class sstackk {
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
        if (index == -1) ans = -1;
        else {
            ans = s[index];
            index--;
        }
        return ans;
    }

    public int peek() {
        if (index == -1) return -1;
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

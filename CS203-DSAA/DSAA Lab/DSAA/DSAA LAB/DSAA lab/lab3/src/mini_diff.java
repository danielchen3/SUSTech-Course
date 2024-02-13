import java.io.*;
import java.util.StringTokenizer;

public class mini_diff {
    public static Weight[] weights = new Weight[10000001];

    public static Weight[] weights_fake = new Weight[10000001];

    public static Weight[] temp = new Weight[10000001];

    public static int[] ans = new int[10000001];

    public static void main(String[] args) {
        QReader input = new QReader();
        QWriter output = new QWriter();

        int n = input.nextInt();

        for (int i = 0; i < n; i++) {
            int val = input.nextInt();
            weights[i] = new Weight(val);
        }

        for (int i = 0; i < n; i++) {
            weights_fake[i] = weights[i];
        }

        MergeSort(0, n - 1);

        Weight_list list = new Weight_list();
        Weight temp = list.head;
        for (int i = 0; i < n; i++) {
            temp.next = weights_fake[i];
            weights_fake[i].prev = temp;
            weights_fake[i].next = list.tail;
            list.tail.prev = weights_fake[i];
            temp = temp.next;
        }

        for (int i = 0; i < n - 1; i++) {
            if (weights[i].prev.val == 0) ans[i] = Math.abs(weights[i].val - weights[i].next.val);
            else if (weights[i].next.val == 0) ans[i] = Math.abs(weights[i].val - weights[i].prev.val);
            else
                ans[i] = Math.min(Math.abs(weights[i].val - weights[i].prev.val), Math.abs(weights[i].val - weights[i].next.val));
            //自毁
            weights[i].prev.next = weights[i].next;
            weights[i].next.prev = weights[i].prev;
        }

        for (int i = 0; i < n - 1; i++) {
            if (i != n - 2)
                output.print(ans[i] + " ");
            else output.println(ans[i]);
        }
        output.close();
    }

    protected static void MergeSort(int left, int right) {
        if (left < right) {
            int mid = (left + right) >> 1;
            MergeSort(left, mid);
            MergeSort(mid + 1, right);
            merge(left, mid, right);
        }
    }

    protected static void merge(int left, int mid, int right) {
        int leftpos = left;
        int rightpos = mid + 1;
        int temppos = left;
        while (leftpos <= mid && rightpos <= right) {
            if (weights_fake[leftpos].val <= weights_fake[rightpos].val) temp[temppos++] = weights_fake[leftpos++];
            else {
                temp[temppos++] = weights_fake[rightpos++];
            }
        }
        while (leftpos <= mid) {
            temp[temppos++] = weights_fake[leftpos++];
        }
        while (rightpos <= right) {
            temp[temppos++] = weights_fake[rightpos++];
        }
        for (int i = left; i <= right; i++)
            weights_fake[i] = temp[i];
    }
}


class Weight_list {
    Weight tail = new Weight(0);

    Weight head = new Weight(0);

    Weight temp = head;

    public void add(Weight weight) {
        if (temp.next == null) {
            temp.next = weight;
            weight.prev = temp;
            weight.next = tail;
            tail.prev = weight;
            temp = temp.next;
        }
    }
}


class Weight {
    Weight next;

    Weight prev;

    int val;

    public Weight(int val) {
        this.val = val;
    }
}
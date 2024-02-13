import java.io.*;
import java.util.StringTokenizer;

public class Ksmall {

    public static int[] a = new int[1000001];
    public static int[] b = new int[1000001];

    public static int[] temp = new int[1000001];

    public static int[] tempb = new int[1000001];

    public static Cardi[] mul = new Cardi[1000001];

    public static int size = 0;

    public static void main(String[] args) {
        QReader input = new QReader();
        QWriter output = new QWriter();

        int N = input.nextInt();
        int M = input.nextInt();
        int K = input.nextInt();

        for (int i = 1; i <= N; i++) a[i] = input.nextInt();
        for (int i = 1; i <= M; i++) b[i] = input.nextInt();

        //
        MergeSort(1, N);
        for (int j = 1; j <= M; j++) {
            int i = 1;
            Cardi cardi = new Cardi();
            cardi.i = i;
            cardi.j = j;
            put(cardi);
            check(size);
        }


        while (K > 0) {
            Cardi ans = delete();
            if (ans != null)
                output.print((long) a[ans.i] * (long) b[ans.j] + " ");
            Cardi cardi = new Cardi();
            if (ans != null && ans.i < N) {
                cardi.i = ans.i + 1;
                cardi.j = ans.j;
                put(cardi);
                check(size);
            }
            K--;
        }

        output.close();
    }

    //先把堆顶删掉，然后adjust，最后插入新的数

    public static Cardi delete() {
        if (size == 0) return null;
        Cardi ans = mul[1];
        mul[1] = mul[size];
        mul[size] = null;
        size--;
        adjust();
        return ans;
    }

    //删除之后进行调整
    public static void adjust() {
        int start = 1;
        while (true) {
            int temp = start;
            if (temp * 2 + 1 <= size) {
                if (get_val(mul[temp]) > get_val(mul[temp * 2 + 1]) && get_val(mul[temp * 2]) > get_val(mul[temp * 2 + 1]))
                    temp = temp * 2 + 1;
                else if (get_val(mul[temp]) > get_val(mul[temp * 2]) && get_val(mul[temp * 2 + 1]) > get_val(mul[temp * 2]))
                    temp = temp * 2;
                else if (get_val(mul[temp]) > get_val(mul[temp * 2])) temp = temp * 2;
                else if (get_val(mul[temp]) > get_val(mul[temp * 2 + 1])) temp = temp * 2 + 1;
            } else if (temp * 2 <= size) {
                if (get_val(mul[temp]) > get_val(mul[temp * 2])) temp = temp * 2;
            }
            if (temp == start) break;
            swap(temp, start);
            start = temp;
        }
    }

    public static long get_val(Cardi cardi) {
        return (long) a[cardi.i] * (long) b[cardi.j];
    }

    public static void put(Cardi cardi) {
        size++;
        mul[size] = cardi;
        //cardi.index = size;
    }

    public static void check(int pos) {
        while (pos > 1 && (long) a[mul[pos].i] * (long) b[mul[pos].j] < (long) a[mul[pos / 2].i] * (long) b[mul[pos / 2].j]) {
            swap(pos, pos / 2);
            pos = pos / 2;
        }
    }

    public static void swap(int i, int j) {
        Cardi tmp = mul[i];
        mul[i] = mul[j];
        mul[j] = tmp;
    }

    protected static void MergeSort(int left, int right) {
        if (left >= right) return;
        int mid = (left + right) / 2;
        MergeSort(left, mid);
        MergeSort(mid + 1, right);
        merge(left, mid, right);
    }

    protected static void merge(int left, int mid, int right) {
        int leftpos = left;
        int rightpos = mid + 1;
        int temppos = left;

        //分成两组，并且按照大小顺序加入新的数组
        while (leftpos <= mid && rightpos <= right) {
            if (a[leftpos] < a[rightpos]) temp[temppos++] = a[leftpos++];
            else temp[temppos++] = a[rightpos++];
        }

        //把还没加进去的数字加进去
        while (leftpos <= mid) {
            temp[temppos++] = a[leftpos++];
        }
        while (rightpos <= right) {
            temp[temppos++] = a[rightpos++];
        }
        for (int i = left; i <= right; i++)
            a[i] = temp[i];
    }

    protected static void MergeSortB(int left, int right) {
        if (left >= right) return;
        int mid = (left + right) / 2;
        MergeSortB(left, mid);
        MergeSortB(mid + 1, right);
        mergeB(left, mid, right);
    }

    protected static void mergeB(int left, int mid, int right) {
        int leftpos = left;
        int rightpos = mid + 1;
        int temppos = left;

        //分成两组，并且按照大小顺序加入新的数组
        while (leftpos <= mid && rightpos <= right) {
            if (b[leftpos] < b[rightpos]) tempb[temppos++] = b[leftpos++];
            else tempb[temppos++] = b[rightpos++];
        }

        //把还没加进去的数字加进去
        while (leftpos <= mid) {
            tempb[temppos++] = b[leftpos++];
        }
        while (rightpos <= right) {
            tempb[temppos++] = b[rightpos++];
        }
        for (int i = left; i <= right; i++)
            b[i] = tempb[i];
    }
}

class Cardi {
    int i;
    int j;

    int index;
}



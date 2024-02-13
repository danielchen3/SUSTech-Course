import java.io.*;
import java.util.StringTokenizer;

public class lowbie {

    public static Gift[] init_list = new Gift[1000001];

    public static Gift[] copy_list = new Gift[1000001];

    public static Gift[] temp = new Gift[1000001];

    public static int[] pos = new int[1000001];

    public static int[] init_seq = new int[1000001];

    public static void main(String[] args) {
        QReader input = new QReader();
        QWriter output = new QWriter();

        int T = input.nextInt();
        for (int i = 0; i < T; i++) {
            int[] ans = new int[1000001];
            int n = input.nextInt();
            for (int j = 0; j < n; j++) {
                int val = input.nextInt();
                init_list[j] = new Gift(val);
                init_seq[j] = val;
                init_list[j].pos1 = j;
            }
            for (int j = 0; j < n; j++) {
                copy_list[j] = init_list[j];
            }

            MergeSort(0, n - 1);

//            for (int j = 0; j < n; j++) {
//                output.print(j + " " + pos[j] + "\n");
//            }

            gift_given list = new gift_given();
            Gift temp = list.head;

            //将排序之后的数组连成双向链表
            for (int j = 0; j < n; j++) {
                temp.next = copy_list[j];
                copy_list[j].prev = temp;
                copy_list[j].next = list.tail;
                list.tail.prev = copy_list[j];
                temp = temp.next;
            }

            int move = 0;


            int mid = n / 2;
            for (int j = n - 1; j >= 0; j--) {
                if (j % 2 == 0) {
                    if (move > 0) mid = pos[copy_list[mid].next.pos1];
                    else if (move < 0) mid = pos[copy_list[mid].prev.pos1];
                    move = 0;
                    ans[j] = copy_list[mid].val;
                }
                if (pos[j] < mid)
                    move++;
                if (pos[j] > mid)
                    move--;
                copy_list[pos[j]].prev.next = copy_list[pos[j]].next;
                copy_list[pos[j]].next.prev = copy_list[pos[j]].prev;
            }

            for (int j = 0; j < n; j++) {
                if ((j == n - 1 && (n - 1) % 2 == 0) || j == n - 2 && (n - 2) % 2 == 0) {
                    output.println(ans[j]);
                    break;
                }
                if (j % 2 == 0) output.print(ans[j] + " ");
            }
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
            if (copy_list[leftpos].val <= copy_list[rightpos].val) {
                temp[temppos] = copy_list[leftpos];
                pos[copy_list[leftpos].pos1] = temppos;
                temppos++;
                leftpos++;
            } else {
                temp[temppos] = copy_list[rightpos];
                pos[copy_list[rightpos].pos1] = temppos;
                temppos++;
                rightpos++;
            }
        }
        while (leftpos <= mid) {
            temp[temppos] = copy_list[leftpos];
            pos[copy_list[leftpos].pos1] = temppos;
            temppos++;
            leftpos++;
        }
        while (rightpos <= right) {
            temp[temppos] = copy_list[rightpos];
            pos[copy_list[rightpos].pos1] = temppos;
            temppos++;
            rightpos++;
        }
        for (int i = left; i <= right; i++)
            copy_list[i] = temp[i];
    }
}


class gift_given {
    Gift head = new Gift(-1);

    Gift tail = new Gift(-1);
    int size;
    Gift temp = head;

    public void add(Gift gift) {
        if (temp != null) {
            temp.next = gift;
            size++;
            temp = temp.next;
        }
    }
}

class Gift {
    int val;

    Gift next;

    Gift prev;

    int pos1;

    public Gift(int val) {
        this.val = val;
    }
}
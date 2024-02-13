import java.util.Scanner;


//小顶堆维护
//建堆时候维护index,index小的往上放
//双向链表


public class FFT {

    public static int[] arr = new int[1000001];

    public static Tuzi[] tuzis = new Tuzi[1000001];

    public static int n;

    public static int size = 0;

    public static int linked_list_length;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        size = n;
        Tuzi head = new Tuzi();
        Tuzi tail = new Tuzi();
        Tuzi temp = head;
        for (int i = 1; i <= n; i++) {
            arr[i] = input.nextInt();
            Tuzi tuzi = new Tuzi();
            //赋予tuzi对应的index和val
            tuzi.index = i;
            tuzi.val = arr[i];
            //连成链表
            tuzi.next = tail;
            tail.prev = tuzi;
            tuzi.prev = temp;
            temp.next = tuzi;
            temp = temp.next;
            tuzis[i] = tuzi;
        }
        heapify();

        linked_list_length = n;

        while (linked_list_length > 1) {
            while (!tuzis[1].is_alive) delete();
            if (tuzis[1].prev == null && tuzis[1].next != null) {
                if (tuzis[1].next != tail) {
                    int val = tuzis[1].next.val;
                    tuzis[1].next.is_alive = false;
                    tuzis[1].next.next.prev = tuzis[1];
                    tuzis[1].next = tuzis[1].next.next;
                    tuzis[1].val = (tuzis[1].val ^ val) + 1;
                    linked_list_length--;
                }
            } else if (tuzis[1].prev != null && tuzis[1].next == null) {
                if (tuzis[1].prev != head) {
                    int val = tuzis[1].prev.val;
                    tuzis[1].prev.is_alive = false;
                    tuzis[1].prev.prev.next = tuzis[1];
                    tuzis[1].prev = tuzis[1].prev.prev;
                    tuzis[1].val = (tuzis[1].val ^ val) + 1;
                    linked_list_length--;
                }
            } else if (tuzis[1].prev != null && tuzis[1].next != null) {
                if (tuzis[1].prev != head && tuzis[1].next != tail) {
                    int val1 = (tuzis[1].prev.val ^ tuzis[1].val) + 1;
                    int val2 = (tuzis[1].next.val ^ tuzis[1].val) + 1;
                    if (val1 >= val2) {
                        tuzis[1].prev.is_alive = false;
                        tuzis[1].prev.prev.next = tuzis[1];
                        tuzis[1].prev = tuzis[1].prev.prev;
                        tuzis[1].val = val1;
                        linked_list_length--;
                    } else {
                        tuzis[1].next.is_alive = false;
                        tuzis[1].next.next.prev = tuzis[1];
                        tuzis[1].next = tuzis[1].next.next;
                        tuzis[1].val = val2;
                        linked_list_length--;
                    }
                } else if (tuzis[1].prev == head && tuzis[1].next != tail) {
                    int val = tuzis[1].next.val;
                    tuzis[1].next.is_alive = false;
                    tuzis[1].next.next.prev = tuzis[1];
                    tuzis[1].next = tuzis[1].next.next;
                    tuzis[1].val = (tuzis[1].val ^ val) + 1;
                    linked_list_length--;
                } else if (tuzis[1].prev != head && tuzis[1].next == tail) {
                    int val = tuzis[1].prev.val;
                    tuzis[1].prev.is_alive = false;
                    tuzis[1].prev.prev.next = tuzis[1];
                    tuzis[1].prev = tuzis[1].prev.prev;
                    tuzis[1].val = (tuzis[1].val ^ val) + 1;
                    linked_list_length--;
                }
            }
            adjust();
        }

        System.out.println(tuzis[1].val);

    }


    //root-fix建堆
    //按照index顺序
    public static void heapify() {
        for (int i = n; i >= 1; i--) {
            int temp = i;
            while (true) {
                int cnt = temp;
                if (temp * 2 + 1 <= n) {
                    if (tuzis[temp].val > tuzis[temp * 2 + 1].val && tuzis[temp * 2].val > tuzis[temp * 2 + 1].val) {
                        swap(temp, temp * 2 + 1);
                        temp = temp * 2 + 1;
                    } else if (tuzis[temp].val > tuzis[temp * 2].val && tuzis[temp * 2 + 1].val > tuzis[temp * 2].val) {
                        swap(temp, temp * 2);
                        temp = temp * 2;
                    } else if (tuzis[temp].val > tuzis[temp * 2].val) {
                        swap(temp, temp * 2);
                        temp = temp * 2;
                    } else if (tuzis[temp].val > tuzis[temp * 2 + 1].val) {
                        swap(temp, temp * 2 + 1);
                        temp = temp * 2 + 1;
                    } else if (tuzis[temp].val == tuzis[temp * 2].val) {
                        if (tuzis[temp].val == tuzis[temp * 2 + 1].val) {
                            if (temp != find_smallest(tuzis[temp].index, tuzis[temp * 2].index, tuzis[temp * 2 + 1].index)) {
                                swap(temp, find_smallest(tuzis[temp].index, tuzis[temp * 2].index, tuzis[temp * 2 + 1].index));
                                temp = find_smallest(tuzis[temp].index, tuzis[temp * 2].index, tuzis[temp * 2 + 1].index);
                            }
                        } else {
                            if (tuzis[temp].index > tuzis[temp * 2].index) {
                                swap(temp, temp * 2);
                                temp = temp * 2;
                            }
                        }
                    } else if (tuzis[temp].val == tuzis[temp * 2 + 1].val) {
                        if (tuzis[temp].val == tuzis[temp * 2].val) {
                            if (temp != find_smallest(tuzis[temp].index, tuzis[temp * 2].index, tuzis[temp * 2 + 1].index)) {
                                swap(temp, find_smallest(tuzis[temp].index, tuzis[temp * 2].index, tuzis[temp * 2 + 1].index));
                                temp = find_smallest(tuzis[temp].index, tuzis[temp * 2].index, tuzis[temp * 2 + 1].index);
                            }
                        } else {
                            if (tuzis[temp].index > tuzis[temp * 2 + 1].index) {
                                swap(temp, temp * 2 + 1);
                                temp = temp * 2 + 1;
                            }
                        }
                    }
                } else if (temp * 2 <= n) {
                    if (tuzis[temp].val > tuzis[temp * 2].val || (tuzis[temp].val == tuzis[temp * 2].val && tuzis[temp].index > tuzis[temp * 2].index)) {
                        swap(temp, temp * 2);
                        temp = temp * 2;
                    } else if (tuzis[temp].val == tuzis[temp * 2].val) {
                        if (tuzis[temp].index > tuzis[temp * 2].index) {
                            swap(temp, temp * 2);
                            temp = temp * 2;
                        }
                    }
                }
                if (cnt == temp) break;
            }
        }
    }


    public static int find_smallest(int i, int j, int k) {
        if (i < j && i < k) return i;
        else if (j < i && j < k) return j;
        else return k;
    }

    public static void adjust() {
        if (linked_list_length == 1) return;
        int start = 1;
        while (true) {
            int temp = start;
            if (temp * 2 + 1 <= size) {
                if (tuzis[temp].val > tuzis[temp * 2 + 1].val && tuzis[temp * 2].val > tuzis[temp * 2 + 1].val)
                    temp = temp * 2 + 1;
                else if (tuzis[temp].val > tuzis[temp * 2].val && tuzis[temp * 2 + 1].val > tuzis[temp * 2].val)
                    temp = temp * 2;
                else if (tuzis[temp].val > tuzis[temp * 2].val && tuzis[temp * 2].index < tuzis[temp * 2 + 1].index) {
                    temp = temp * 2;
                } else if (tuzis[temp].val > tuzis[temp * 2 + 1].val && tuzis[temp * 2].index > tuzis[temp * 2 + 1].index)
                    temp = temp * 2 + 1;
                else if (tuzis[temp].val == tuzis[temp * 2].val) {
                    if (tuzis[temp].val == tuzis[temp * 2 + 1].val) {
                        if (tuzis[temp].index != find_smallest(tuzis[temp].index, tuzis[temp * 2].index, tuzis[temp * 2 + 1].index)) {
                            int ffindd = find_smallest(tuzis[temp].index, tuzis[temp * 2].index, tuzis[temp * 2 + 1].index);
                            if (ffindd == tuzis[temp * 2].index) temp = temp * 2;
                            else temp = temp * 2 + 1;
                        }
                    } else {
                        if (tuzis[temp].index > tuzis[temp * 2].index) {
                            temp = temp * 2;
                        }
                    }
                } else if (tuzis[temp].val == tuzis[temp * 2 + 1].val) {
                    if (tuzis[temp].val == tuzis[temp * 2].val) {
                        if (tuzis[temp].index != find_smallest(tuzis[temp].index, tuzis[temp * 2].index, tuzis[temp * 2 + 1].index)) {
                            int ffindd = find_smallest(tuzis[temp].index, tuzis[temp * 2].index, tuzis[temp * 2 + 1].index);
                            if (ffindd == tuzis[temp * 2].index) temp = temp * 2;
                            else temp = temp * 2 + 1;
                        }
                    } else {
                        if (tuzis[temp].index > tuzis[temp * 2 + 1].index) {
                            temp = temp * 2 + 1;
                        }
                    }
                }
            } else if (temp * 2 <= size) {
                if (tuzis[temp].val > tuzis[temp * 2].val) temp = temp * 2;
                else if (tuzis[temp].val == tuzis[temp * 2].val) {
                    if (tuzis[temp].index > tuzis[temp * 2].index) {
                        temp = temp * 2;
                    }
                }
            }
            if (temp == start) break;
            swap(temp, start);
            start = temp;
        }
    }

    public static Tuzi delete() {
        if (size == 0) return null;
        Tuzi ans = tuzis[1];
        tuzis[1] = tuzis[size];
        tuzis[size] = null;
        size--;
        adjust();
        return ans;
    }

    public static void swap(int i, int j) {
        Tuzi tuzi = tuzis[i];
        tuzis[i] = tuzis[j];
        tuzis[j] = tuzi;
    }


}

class Tuzi {
    int index;
    boolean is_alive = true;
    int val;

    Tuzi next;
    Tuzi prev;
}

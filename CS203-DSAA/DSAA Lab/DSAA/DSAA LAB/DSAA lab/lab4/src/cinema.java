import java.io.*;
import java.util.StringTokenizer;

public class cinema {
    public static void main(String[] args) {
        QReader input = new QReader();
        QWriter output = new QWriter();
        int n = input.nextInt();
        Line line1 = new Line();
        Line line2 = new Line();
        Customer[] l1 = new Customer[1000001];
        Customer[] l2 = new Customer[1000001];
        int[] ans = new int[1000001];
        int p = input.nextInt();
        int q = input.nextInt();


        Customer sb = line1.head;
        for (int i = 0; i < p; i++) {
            int x = input.nextInt();
            l1[x] = new Customer(x);
            sb.next = l1[x];
            l1[x].prev = sb;
            l1[x].next = line1.tail;
            line1.tail.prev = l1[x];
            sb = sb.next;
        }

        sb = line2.head;
        for (int i = 0; i < q; i++) {
            int x = input.nextInt();
            l2[x] = new Customer(x);
            sb.next = l2[x];
            l2[x].prev = sb;
            l2[x].next = line2.tail;
            line2.tail.prev = l2[x];
            sb = sb.next;
        }

        Customer line1_tt = line1.head.next;
        Customer line2_tt = line2.head.next;
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (line1_tt == line1.tail && line2_tt == line2.tail) break;
            else if (line1_tt != line1.tail && line2_tt != line2.tail) {
                if (line1_tt.val == line2_tt.val) {
                    l2[line2_tt.val].prev.next = l2[line2_tt.val].next;
                    l2[line2_tt.val].next.prev = l2[line2_tt.val].prev;
                    l1[line1_tt.val].prev.next = l1[line1_tt.val].next;
                    l1[line1_tt.val].next.prev = l1[line1_tt.val].prev;
                    l1[line1_tt.val].is_inthelist = false;
                    l2[line2_tt.val].is_inthelist = false;
                    cnt++;
                    ans[line1_tt.val] = cnt;
                } else {
                    if (l2[line1_tt.val] != null && l2[line1_tt.val].is_inthelist) {
                        l2[line1_tt.val].prev.next = l2[line1_tt.val].next;
                        l2[line1_tt.val].next.prev = l2[line1_tt.val].prev;
                        l2[line1_tt.val].is_inthelist = false;
                    }
                    if (l1[line2_tt.val] != null && l1[line2_tt.val].is_inthelist) {
                        l1[line2_tt.val].prev.next = l1[line2_tt.val].next;
                        l1[line2_tt.val].next.prev = l1[line2_tt.val].prev;
                        l1[line2_tt.val].is_inthelist = false;
                    }
                    l2[line2_tt.val].prev.next = l2[line2_tt.val].next;
                    l2[line2_tt.val].next.prev = l2[line2_tt.val].prev;
                    l1[line1_tt.val].prev.next = l1[line1_tt.val].next;
                    l1[line1_tt.val].next.prev = l1[line1_tt.val].prev;
                    l1[line1_tt.val].is_inthelist = false;
                    l2[line2_tt.val].is_inthelist = false;
                    cnt++;
                    ans[line1_tt.val] = cnt;
                    ans[line2_tt.val] = cnt;
                }
            } else if (line1_tt != line1.tail) {
                l1[line1_tt.val].prev.next = l1[line1_tt.val].next;
                l1[line1_tt.val].next.prev = l1[line1_tt.val].prev;
                l1[line1_tt.val].is_inthelist = false;
                cnt++;
                ans[line1_tt.val] = cnt;
            } else {
                l2[line2_tt.val].prev.next = l2[line2_tt.val].next;
                l2[line2_tt.val].next.prev = l2[line2_tt.val].prev;
                l2[line2_tt.val].is_inthelist = false;
                cnt++;
                ans[line2_tt.val] = cnt;
            }
            while (line1_tt != line1.tail && !line1_tt.is_inthelist)
                line1_tt = line1_tt.next;
            while (line2_tt != line2.tail && !line2_tt.is_inthelist)
                line2_tt = line2_tt.next;
        }

        for (int i = 1; i <= n; i++) {
            if (i != n) output.print(ans[i] + " ");
            else output.println(ans[i]);
        }

        output.close();
    }
}

class Line {
    Customer head = new Customer(-1);

    Customer tail = new Customer(1000001);

    Customer temp = head;

    public void add(Customer customer) {
        temp.next = customer;
        temp = temp.next;
    }
}

class Customer {
    int val;

    Customer next;

    Customer prev;

    boolean is_inthelist = true;

    public Customer(int val) {
        this.val = val;
    }
}
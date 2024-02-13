import java.io.*;
import java.util.StringTokenizer;

public class nonde {

    public static Num[] penal = new Num[10000001];

    public static Num[] ssl = new Num[10000001];

    public static void main(String[] args) {
        QReader input = new QReader();
        QWriter output = new QWriter();

        int T = input.nextInt();

        for (int i = 0; i < T; i++) {
            int n = input.nextInt();
            num_list list1 = new num_list();
            Num ttemp = list1.head;
            for (int j = 0; j < n; j++) {
                int x = input.nextInt();
                Num soldier = new Num(x);
                ttemp.next = soldier;
                soldier.prev = ttemp;
                soldier.next = list1.tail;
                list1.tail.prev = soldier;
                ttemp = ttemp.next;
            }

            Num tmp = list1.head;

            int cnt = 0;
            //删第一轮，并找到每删除一段前一个数作为潜在发起者
            while (tmp != null && tmp.next != null) {
                Num ll = tmp;
                boolean judge = false;
                while (ll.next != null && ll.val > ll.next.val) {
                    if (!judge) {
                        penal[cnt] = ll.prev;
                        cnt++;
                        if (cnt >= 2 && penal[cnt - 1] == penal[cnt - 2]) cnt--;
                        judge = true;
                    }
                    if (ll.val != -1 && ll.val != 1000001) {
                        ll.isinthelist = false;
                        ll.next.isinthelist = false;
                    }
                    ll = ll.next;
                }
                if (judge) {
                    Num s1 = tmp.prev;
                    Num s2 = ll.next;
                    s1.next = s2;
                    s2.prev = s1;
                    tmp = s1.next;
                } else tmp = tmp.next;
            }

            //output.println(cnt);
//            Num ss = list1.head;
//            while (ss != null) {
//                if (ss.next != null && ss.next.val == 1000001) {
//                    output.println(ss.val);
//                    break;
//                }
//                if (ss.val != -1)
//                    output.print(ss.val + " ");
//                ss = ss.next;
//            }


            while (cnt != 0) {
                int sus = cnt;
                cnt = 0;
                for (int sle = 0; sle < sus; sle++) {
                    ssl[sle] = penal[sle];
                    penal[sle] = null;
                }
                for (int j = 0; j < sus; j++) {
                    if (!ssl[j].isinthelist) continue;
                    Num ll = ssl[j];
                    Num tt = ll;
                    boolean judge = false;
                    while (tt.next != null && tt.val > tt.next.val) {
                        if (!judge) {
                            penal[cnt] = tt.prev;
                            cnt++;
                            if (cnt >= 2 && penal[cnt - 1] == penal[cnt - 2]) cnt--;
                            judge = true;
                        }
                        if (tt.val != -1 && tt.val != 1000001) {
                            tt.isinthelist = false;
                            tt.next.isinthelist = false;
                        }
                        tt = tt.next;
                    }
                    if (judge) {
                        Num s1 = ll.prev;
                        Num s2 = tt.next;
                        s1.next = s2;
                        s2.prev = s1;
                    }
                }
                //output.println(cnt);
//                Num sl = list1.head;
//                while (sl != null) {
//                    if (sl.next != null && sl.next.val == 1000001) {
//                        output.println(sl.val);
//                        break;
//                    }
//                    if (sl.val != -1)
//                        output.print(sl.val + " ");
//                    sl = sl.next;
//                }
            }

            Num mm = list1.head;
            while (mm != null) {
                if (mm.next != null && mm.next.val == 1000001) {
                    if (mm.val != -1)
                        output.println(mm.val);
                    if (mm.val == -1)
                        output.print("\n");
                    break;
                }
                if (mm.val != -1)
                    output.print(mm.val + " ");
                mm = mm.next;
            }
        }
        output.close();
    }

    public static boolean is_non_decreasing(num_list list1) {
        Num mt = list1.head;
        while (mt.next != null) {
            if (mt.val > mt.next.val)
                return false;
            mt = mt.next;
        }
        return true;
    }
}

class num_list {

    Num head = new Num(-1);

    Num tail = new Num(1000001);

}

class Num {
    int val;

    Num next;

    Num prev;

    boolean isinthelist = true;

    public Num(int val) {
        this.val = val;
    }
}


class QReader {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private StringTokenizer tokenizer = new StringTokenizer("");

    private String innerNextLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    public boolean hasNext() {
        while (!tokenizer.hasMoreTokens()) {
            String nextLine = innerNextLine();
            if (nextLine == null) {
                return false;
            }
            tokenizer = new StringTokenizer(nextLine);
        }
        return true;
    }

    public String nextLine() {
        tokenizer = new StringTokenizer("");
        return innerNextLine();
    }

    public String next() {
        hasNext();
        return tokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public long nextLong() {
        return Long.parseLong(next());
    }
}

class QWriter implements Closeable {
    private BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public void print(Object object) {
        try {
            writer.write(object.toString());
        } catch (IOException e) {
            return;
        }
    }

    public void println(Object object) {
        try {
            writer.write(object.toString());
            writer.write("\n");
        } catch (IOException e) {
            return;
        }
    }

    @Override
    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            return;
        }
    }
}
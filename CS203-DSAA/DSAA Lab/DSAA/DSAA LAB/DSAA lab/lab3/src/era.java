import java.io.*;
import java.util.StringTokenizer;

public class era {

    public static Soldier[] arr = new Soldier[1000001];

    public static void main(String[] args) {
        QReader input = new QReader();
        QWriter output = new QWriter();
        int T = input.nextInt();
        for (int i = 0; i < T; i++) {
            int N = input.nextInt();
            int M = input.nextInt();
            soldier_list list = new soldier_list();
            Soldier ttemp = list.head;
            for (int j = 0; j < N; j++) {
                int id = input.nextInt();
                Soldier soldier = new Soldier(id);
                ttemp.next = soldier;
                soldier.prev = ttemp;
                soldier.next = list.tail;
                list.tail.prev = ttemp;
                ttemp = ttemp.next;
                arr[id] = soldier;
            }
            for (int k = 0; k < M; k++) {
                int x1 = input.nextInt();
                int y1 = input.nextInt();
                int x2 = input.nextInt();
                int y2 = input.nextInt();

                boolean judge = (arr[y1].next != arr[x2]);

                Soldier s1 = arr[x1].prev;

                Soldier s4 = arr[y2].next;

                Soldier s2 = arr[y1].next;
                Soldier s3 = arr[x2].prev;

                s1.next = arr[x2];
                arr[x2].prev = arr[x1].prev;

                arr[y1].next = arr[y2].next;
                s4.prev = arr[y1];
                if (judge) {

                    s3.next = arr[x1];
                    arr[x1].prev = s3;

                    arr[y2].next = s2;
                    s2.prev = arr[y2];
                } else {
                    arr[x1].prev = arr[y2];
                    arr[y2].next = arr[x1];
                }

            }
            Soldier temp = list.head;
            while (temp != null) {
                if (temp.next != null && temp.next.id == -1) {
                    output.println(temp.id);
                    break;
                }
                if (temp.id != -1)
                    output.print(temp.id + " ");
                temp = temp.next;
            }
        }
        output.close();
    }
}


class soldier_list {
    Soldier head = new Soldier(-1);

    Soldier tail = new Soldier(-1);
    public Soldier temp = head;

    public void add_soldier(Soldier s) {
        if (temp.next == null) {
            temp.next = s;
            s.prev = temp;
            temp.next = tail;
            tail.prev = temp;
            temp = temp.next;
        }
    }
}

class Soldier {
    int id;

    Soldier next;

    Soldier prev;

    public Soldier(int id) {
        this.id = id;
    }
}
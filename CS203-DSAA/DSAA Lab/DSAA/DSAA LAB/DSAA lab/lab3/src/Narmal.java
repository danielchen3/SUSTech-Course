import java.io.*;
import java.util.StringTokenizer;

public class Narmal {
    public static void main(String[] args) {
        QReader input = new QReader();
        QWriter output = new QWriter();
        int T = input.nextInt();
        for (int i = 0; i < T; i++) {
            int n = input.nextInt();
            ope_vinux vinux1 = new ope_vinux();
            ope_vinux vinux_out = new ope_vinux();
            String s = input.next();
            for (int j = 0; j < n; j++) {
                char c = s.charAt(j);
                Vinux vv = new Vinux(c);
                vinux1.add_nextly(vv);
            }
            vinux_out.head.next = vinux_out.tail;
            vinux_out.tail.prev = vinux_out.head;
            Vinux vv = vinux1.head.next;
            while (vv != null) {
                if (vv.s == 'r') {
                    if (vv.next != null) {
                        vv = vv.next;
                        if (vinux_out.pointer != null && vinux_out.pointer.s == 'M') {
                            Vinux vs = new Vinux(vv.s);
//                            vv_head.next = vs;
//                            vs.prev = vv_head;
//                            vs.next = vinux_out.pointer;
//                            vinux_out.pointer.prev = vs;
//                            vinux_out.pointer = vinux_out.pointer.prev;
                            vinux_out.pointer.prev.next = vs;
                            vs.prev = vinux_out.pointer.prev;
                            vs.next = vinux_out.pointer;
                            vinux_out.pointer.prev = vs;
                            vinux_out.pointer = vinux_out.pointer.prev;
                        } else if (vinux_out.pointer != null)
                            vinux_out.pointer.s = vv.s;
                    }
                } else if (vv.s == 'H') {
                    if (vinux_out.pointer != null && vinux_out.pointer.prev.s != 'T')
                        vinux_out.pointer = vinux_out.pointer.prev;
                } else if (vv.s == 'I') {
                    while (vinux_out.pointer != null && vinux_out.pointer.prev.s != 'T')
                        vinux_out.pointer = vinux_out.pointer.prev;
                } else if (vv.s == 'L') {
                    if (vinux_out.pointer != null && vinux_out.pointer.s != 'M')
                        vinux_out.pointer = vinux_out.pointer.next;
                } else if (vv.s == 'x') {
                    if (vinux_out.pointer != null && vinux_out.pointer.s != 'T' && vinux_out.pointer.s != 'M') {
                        vinux_out.pointer.prev.next = vinux_out.pointer.next;
                        vinux_out.pointer.next.prev = vinux_out.pointer.prev;
                        vinux_out.pointer = vinux_out.pointer.next;
                    }
                } else {
                    Vinux vs = new Vinux(vv.s);
//                    if (vinux_out.pointer != null && vinux_out.pointer.prev != null)
//                        vv_head = vinux_out.pointer.prev;
//                    vv_head.next = vs;
//                    vs.prev = vv_head;
//                    vs.next = vinux_out.pointer;
//                    vinux_out.pointer.prev = vs;
                    if (vinux_out.pointer != null) {
                        vinux_out.pointer.prev.next = vs;
                        vs.prev = vinux_out.pointer.prev;
                        vs.next = vinux_out.pointer;
                        vinux_out.pointer.prev = vs;
                    }
                }
                vv = vv.next;
            }
            Vinux vv_out = vinux_out.head.next;
            while (vv_out != null) {
                if (vv_out.s == 'M') {
                    output.print("\n");
                    break;
                }
                output.print(vv_out.s);
                vv_out = vv_out.next;
            }
        }
        output.close();
    }
}

class ope_vinux {

    Vinux head = new Vinux('T');

    Vinux tail = new Vinux('M');

    Vinux temp = head;

    Vinux pointer = tail;


    public void add_nextly(Vinux vinux) {
        if (temp.next == null) {
            temp.next = vinux;
            temp = temp.next;
        }
    }
}


class Vinux {
    char s;

    Vinux prev;

    Vinux next;

    public Vinux(char s) {
        this.s = s;
    }
}
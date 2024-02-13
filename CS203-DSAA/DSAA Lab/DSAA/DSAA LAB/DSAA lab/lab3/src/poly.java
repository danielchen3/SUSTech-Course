import java.io.*;
import java.util.StringTokenizer;

public class poly {
    public static void main(String[] args) {
        poly_ope poly1 = new poly_ope();
        poly_ope poly2 = new poly_ope();
        poly_ope output_poly = new poly_ope();
        QReader input = new QReader();
        QWriter output = new QWriter();
        int n = input.nextInt();
        int m = input.nextInt();
        long bigg_ex1 = 0, bigg_ex2 = 0;
        //创建两个链表并添加元素
        for (int i = 0; i < n; i++) {
            long co = input.nextInt();
            long ex = input.nextInt();
            if (i == 0) bigg_ex1 = ex;
            poly1.add_new_poly(new Polynomial(co, ex));
        }
        for (int i = 0; i < m; i++) {
            long co = input.nextInt();
            long ex = input.nextInt();
            if (i == 0) bigg_ex2 = ex;
            poly2.add_new_poly(new Polynomial(co, ex));
        }

        Polynomial temp1 = poly1.head.next;
        Polynomial temp2 = poly2.head.next;

        if (bigg_ex1 > bigg_ex2) {
            while (temp2 != null && temp1 != null) {
                if (temp2.ex > temp1.ex) {
                    if (temp2.co != 0)
                        output_poly.add_new_poly(new Polynomial(temp2.co, temp2.ex));
                    temp2 = temp2.next;
                } else if (temp1.ex == temp2.ex) {
                    if (temp2.co + temp1.co != 0)
                        output_poly.add_new_poly(new Polynomial(temp2.co + temp1.co, temp2.ex));
                    temp2 = temp2.next;
                    temp1 = temp1.next;
                } else {
                    if (temp1.co != 0)
                        output_poly.add_new_poly(new Polynomial(temp1.co, temp1.ex));
                    temp1 = temp1.next;
                }
            }
            while (temp1 != null) {
                if (temp1.co != 0)
                    output_poly.add_new_poly(new Polynomial(temp1.co, temp1.ex));
                temp1 = temp1.next;
            }
            while (temp2 != null) {
                if (temp2.co != 0)
                    output_poly.add_new_poly(new Polynomial(temp2.co, temp2.ex));
                temp2 = temp2.next;
            }
        } else {
            while (temp2 != null && temp1 != null) {
                if (temp1.ex > temp2.ex) {
                    if (temp1.co != 0)
                        output_poly.add_new_poly(new Polynomial(temp1.co, temp1.ex));
                    temp1 = temp1.next;
                } else if (temp1.ex == temp2.ex) {
                    if (temp2.co + temp1.co != 0)
                        output_poly.add_new_poly(new Polynomial(temp2.co + temp1.co, temp2.ex));
                    temp2 = temp2.next;
                    temp1 = temp1.next;
                } else {
                    if (temp2.co != 0)
                        output_poly.add_new_poly(new Polynomial(temp2.co, temp2.ex));
                    temp2 = temp2.next;
                }
            }
            while (temp1 != null) {
                if (temp1.co != 0)
                    output_poly.add_new_poly(new Polynomial(temp1.co, temp1.ex));
                temp1 = temp1.next;
            }
            while (temp2 != null) {
                if (temp2.co != 0)
                    output_poly.add_new_poly(new Polynomial(temp2.co, temp2.ex));
                temp2 = temp2.next;
            }
        }

        output.println(output_poly.size);

        Polynomial temp = output_poly.head.next;
        while (temp != null) {
            output.println(temp.co + " " + temp.ex);
            temp = temp.next;
        }

        output.close();
    }

}

class poly_ope {
    public int size = 0;

    public Polynomial head = new Polynomial(0, 0);

    public Polynomial temp = head;

    public void add_new_poly(Polynomial polynomial) {
        if (temp.next == null) {
            temp.next = polynomial;
            temp = temp.next;
            size++;
        }
    }
}

class Polynomial {
    long co;
    long ex;

    Polynomial next;

    public Polynomial(long co, long ex) {
        this.co = co;
        this.ex = ex;
    }
}
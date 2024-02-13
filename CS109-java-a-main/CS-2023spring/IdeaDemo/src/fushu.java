import java.util.Scanner;
public class fushu {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.printf("Please enter in initial numbers: ");
        int i1,i2;
        i1= input.nextInt();
        i2= input.nextInt();
        fushu2 cal = new fushu2(i1,i2);
        //System.out.println();
        //System.out.printf("%s",s.trim());
        for (int i = 0; i < 1000; i++) {
            int a, b;
            String s;
            System.out.print("Please enter your operations: ");
            s = input.next();
            if (s.trim().equals("-1")) {
                break;
            }
            else {
                System.out.printf("Please enter your numbers: ");
                a = input.nextInt();
                b = input.nextInt();
                if ((s.trim()).equals("+")) cal.get_add(a, b);
                else if ((s.trim()).equals("-")) cal.get_minus(a, b);
                else if ((s.trim()).equals("*")) cal.get_mul(a, b);
                else {
                    System.out.printf("ERROR!");
                    break;
                }
            }
        }
        cal.displayMessage(cal.get_result1(), cal.get_result2());

        //cal.get_fushu(a, b, c, d);
        /*System.out.printf("Your answer is: ");
        if ((s.trim()).equals("+")) {
            if (b + d == 0) System.out.printf("%d", cal.define_add1());
            else if (b + d > 0) System.out.printf("%d+%di", cal.define_add1(), cal.define_add2());
            else System.out.printf("%d%di", cal.define_add1(), cal.define_add2());
        }
        else if ((s.trim()).equals("-")){
            if (b == d) System.out.printf("%d", cal.define_minus1());
            else if (b - d > 0) System.out.printf("%d+%di", cal.define_minus1(), cal.define_minus2());
            else System.out.printf("%d%di", cal.define_minus1(), cal.define_minus2());
        }
        else if ((s.trim()).equals("*")) {
            if (a * d + b * c == 0) System.out.printf("%d", cal.define_mul1());
            else if (a * d + b * c > 0) System.out.printf("%d+%di", cal.define_mul1(), cal.define_mul2());
            else System.out.printf("%d%di", cal.define_mul1(), cal.define_mul2());
        }
        else System.out.printf("ERROR!");
        testpatient patient_infor=new testpatient();*/
    }
}

import java.util.Scanner;

public class p12 {

    public static String intToRoman(int num) {
        String ans = "";

        if (num >= 1000) {
            int div = num / 1000;
            for (int i = 0; i < div; i++)
                ans = ans.concat("M");
            num %= 1000;
        }

        if (num >= 500) {
            if (num >= 900) {
                ans = ans.concat("CM");
                num -= 900;
            } else {
                ans = ans.concat("D");
                num -= 500;
            }
        }

        if (num >= 100) {
            if (num >= 400) {
                ans = ans.concat("CD");
                num -= 400;
            } else {
                int div = num / 100;
                for (int i = 0; i < div; i++)
                    ans = ans.concat("C");
                num %= 100;
            }
        }

        if (num >= 50) {
            if (num >= 90) {
                ans = ans.concat("XC");
                num -= 90;
            } else {
                ans = ans.concat("L");
                num -= 50;
            }
        }

        if (num >= 10) {
            if (num >= 40) {
                ans = ans.concat("XL");
                num -= 40;
            } else {
                int div = num / 10;
                for (int i = 0; i < div; i++)
                    ans = ans.concat("X");
                num %= 10;
            }
        }

        if (num >= 5) {
            if (num >= 9) {
                ans = ans.concat("IX");
                num -= 9;
            } else {
                ans = ans.concat("V");
                num -= 5;
            }
        }


        if (num >= 1) {
            if (num >= 4) {
                ans = ans.concat("IV");

                //can be ignored
                num -= 4;
            } else {
                for (int i = 0; i < num; i++)
                    ans = ans.concat("I");
            }
        }

        return ans;
    }


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        System.out.println(intToRoman(n));
    }
}

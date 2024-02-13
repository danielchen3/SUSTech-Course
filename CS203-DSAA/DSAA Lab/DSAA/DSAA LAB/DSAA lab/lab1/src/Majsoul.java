import javax.swing.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

public class Majsoul {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int T = input.nextInt();
        String[] arr = new String[13];
        for (int k = 0; k < T; k++) {
            for (int i = 0; i < 13; i++)
                arr[i] = input.next();
            int[] a = new int[arr.length];
            for (int i = 0; i < arr.length; i++) {
                if ("W1".equals(arr[i])) a[i] = 33;
                if ("W2".equals(arr[i])) a[i] = 32;
                if ("W3".equals(arr[i])) a[i] = 31;
                if ("W4".equals(arr[i])) a[i] = 30;
                if ("W5".equals(arr[i])) a[i] = 29;
                if ("W6".equals(arr[i])) a[i] = 28;
                if ("W7".equals(arr[i])) a[i] = 27;
                if ("W8".equals(arr[i])) a[i] = 26;
                if ("W9".equals(arr[i])) a[i] = 25;
                if ("T1".equals(arr[i])) a[i] = 24;
                if ("T2".equals(arr[i])) a[i] = 23;
                if ("T3".equals(arr[i])) a[i] = 22;
                if ("T4".equals(arr[i])) a[i] = 21;
                if ("T5".equals(arr[i])) a[i] = 20;
                if ("T6".equals(arr[i])) a[i] = 19;
                if ("T7".equals(arr[i])) a[i] = 18;
                if ("T8".equals(arr[i])) a[i] = 17;
                if ("T9".equals(arr[i])) a[i] = 16;
                if ("Y1".equals(arr[i])) a[i] = 15;
                if ("Y2".equals(arr[i])) a[i] = 14;
                if ("Y3".equals(arr[i])) a[i] = 13;
                if ("Y4".equals(arr[i])) a[i] = 12;
                if ("Y5".equals(arr[i])) a[i] = 11;
                if ("Y6".equals(arr[i])) a[i] = 10;
                if ("Y7".equals(arr[i])) a[i] = 9;
                if ("Y8".equals(arr[i])) a[i] = 8;
                if ("Y9".equals(arr[i])) a[i] = 7;
                if ("E".equals(arr[i])) a[i] = 6;
                if ("S".equals(arr[i])) a[i] = 5;
                if ("W".equals(arr[i])) a[i] = 4;
                if ("N".equals(arr[i])) a[i] = 3;
                if ("B".equals(arr[i])) a[i] = 2;
                if ("F".equals(arr[i])) a[i] = 1;
                if ("Z".equals(arr[i])) a[i] = 0;
            }
            Arrays.sort(a);
            String[] res = {"Z", "F", "B", "N", "W", "S", "E", "Y9", "Y8", "Y7", "Y6", "Y5", "Y4", "Y3", "Y2", "Y1", "T9", "T8", "T7", "T6", "T5", "T4", "T3", "T2", "T1", "W9", "W8", "W7", "W6", "W5", "W4", "W3", "W2", "W1"};
            for (int i = a.length - 1; i >= 0; i--) {
                if (i != 0) System.out.printf("%s ", res[a[i]]);
                else System.out.printf("%s\n", res[a[i]]);
            }
        }
    }
}

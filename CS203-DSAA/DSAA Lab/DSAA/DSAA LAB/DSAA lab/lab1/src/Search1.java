import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.Scanner;

public class Search1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n, T;
        int[] a = new int[1001];
        int[] b = new int[1001];
        boolean[] jud = new boolean[1001];
        n = input.nextInt();
        for (int i = 0; i < n; i++) a[i] = input.nextInt();
        T = input.nextInt();
        for (int i = 0; i < T; i++) {
            b[i] = input.nextInt();
            for (int j = 0; j < n; j++) {
                if (a[j] == b[i]) {
                    jud[i] = true;
                    break;
                }
            }
        }
        for (int i = 0; i < T; i++) {
            if (jud[i]) System.out.println("yes");
            else System.out.println("no");
        }
    }

}

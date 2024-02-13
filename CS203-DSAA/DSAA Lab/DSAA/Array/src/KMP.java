import java.sql.SQLOutput;

public class KMP {
    public static String P, T;
    public static int[] D = new int[9999];

    public static void main(String[] args) {
        P = "45&&*";
        T = "45&&**ddds";
        KMP kmp = new KMP();
        kmp.calc_D();
        //for (int i = 0; i < P.length(); i++)
        //System.out.println(D[i]);
        kmp(T, P);
    }

    public void calc_D() {
        char[] p = P.toCharArray();
        int nP = P.length();
        for (int i = 0; i < nP; i++) {
            D[i] = 0;
        }
        int i = 1, j = 0;
        while (i < nP) {
            if (j < nP && p[i] == p[j]) {
                j++;
                D[i] = j;
                i++;
            } else {
                if (j > 0) {
                    j = D[j - 1];
                } else i++;
            }
        }
    }

    public static void kmp(String T, String P) {
        int nT = T.length();
        int nP = P.length();
        char[] t = T.toCharArray();
        char[] p = P.toCharArray();
        int i = 0, j = 0;
        while (i < nT) {
            if (j < nP && i < nT && t[i] == p[j]) {
                i++;
                j++;
                if (j == nP) {
                    System.out.println("找到了" + (i - j));
                }
            } else {
                if (j > 0) {
                    j = D[j - 1];
                } else i++;
            }
        }
    }
}

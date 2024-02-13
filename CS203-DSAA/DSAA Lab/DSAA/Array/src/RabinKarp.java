public class RabinKarp {
    //l是母串，s是子串
    public static void RK(String small, String large) {
        int n = small.length();
        int m = large.length();
        char[] target = small.toCharArray();
        char[] ini = large.toCharArray();
        int sval = 0, lval = 0;
        int h = 1;
        int r = 256;
        int q = 1001;
        //(计算exp(256,n))
        for (int i = 0; i < n - 1; i++) {
            h = (h * r) % q;
        }

        for (int i = n - 1; i >= 0; i--) {
            lval = (lval * r + (int) ini[i]) % q;
            sval = (sval * r + (int) target[i]) % q;
        }
        System.out.println((int) target[0]);
        System.out.println(h);
        System.out.println(lval);
        System.out.println(sval);
        for (int i = 0; i <= m - n; i++) {
            if (lval == sval) {
                //判断是不是完全相等
                boolean judge = true;
                int j = 0;
                while (j < n) {
                    if (target[j] != ini[j + i]) {
                        judge = false;
                        break;
                    }
                    j++;
                }
                if (judge) {
                    System.out.println("匹配出现，从" + i + "位置开始");
                }
            }
            if (i == m - n) return;
            lval = (lval - h * (int) ini[i] * r + (int) ini[i + n]) % q;
            while (lval < 0) lval += q;
            System.out.println(lval);
        }
    }

    public static void main(String[] args) {
        String s = "123456";
        String t = "234";
        RK(t, s);
    }
}
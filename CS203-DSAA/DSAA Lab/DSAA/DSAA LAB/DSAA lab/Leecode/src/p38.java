import java.util.Scanner;

public class p38 {

    public static String countAndSay(int n) {
        String ans = "";
        if (n == 1) ans = "1";
        else if (n == 2) ans = "11";
        else {
            String last_ans = countAndSay(n - 1);
            char[] last_ans_ = new char[last_ans.length()];
            last_ans_ = last_ans.toCharArray();
            int pos = 0;
            int cnt = 1;
            while (pos < last_ans.length() - 1) {
                if (last_ans_[pos] == last_ans_[pos + 1]) {
                    cnt++;
                    pos++;
                    if (pos == last_ans.length() - 1) {
                        ans += String.valueOf(cnt);
                        ans += String.valueOf(last_ans_[pos]);
                    }
                } else {
                    ans += String.valueOf(cnt);
                    cnt = 1;
                    ans += String.valueOf(last_ans_[pos]);
                    pos++;
                    if (pos == last_ans.length() - 1) {
                        ans += String.valueOf(cnt);
                        ans += String.valueOf(last_ans_[pos]);
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        System.out.println(countAndSay(n));
    }
}

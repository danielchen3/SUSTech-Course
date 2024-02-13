import java.util.Scanner;

public class exercise3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int T = input.nextInt();
        int count = 0;
        while (count < T) {
            count++;
            int ans = 0;
            String s = input.next();
            char[] a = new char[100];
            a = s.toCharArray();
            for (int i = 0; i < s.length(); i++) {
                for (int j = 0; j <= 1; j++) {
                    int left = i;
                    int right = i + j;
                    while (left >= 0 && right < s.length() && a[left] == a[right]) {
                        left--;
                        right++;
                        ans++;
                    }
                }
            }
            System.out.println(ans);
        }
    }
}

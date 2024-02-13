public class huiwen {
    public String longestPalindrome(String s) {
        if (s.length() == 1) return s;
        if (s.length() == 2) {
            if (s.substring(0, 1).equals(s.substring(1, 2))) return s;
            return s.substring(0, 1);
        }
        String ans = s.substring(0, 1);
        int l = s.length();
        for (int i = 1; i < l - 1; i++) {
            int left = i - 1;
            int right = i + 1;
            while (left >= 0 && right <= l - 1 && s.substring(left, left + 1).equals(s.substring(right, right + 1))) {
                if (ans.length() < right - left + 1)
                    ans = s.substring(left, right + 1);
                left--;
                right++;
            }
        }
        for (int i = 0; i < l - 1; i++) {
            int left = i;
            int right = i + 1;
            while (left >= 0 && right <= l - 1 && s.substring(left, left + 1).equals(s.substring(right, right + 1))) {
                if (ans.length() < right - left + 1) ans = s.substring(left, right + 1);
                left--;
                right++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        huiwen huiwen = new huiwen();
        String s = "babad";
        System.out.println(huiwen.longestPalindrome(s));
    }
}

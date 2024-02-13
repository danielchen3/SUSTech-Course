public class kuohao {
    public int longestValidParentheses(String s) {
        int ans = 0;
        if (s.length() == 0 || s.length() == 1) return 0;
        char[] a = s.toCharArray();
        for (int i = 0; i < s.length() - 1; i++) {
            if (a[i] == '(' && a[i + 1] == ')') {
                a[i] = 't';
                a[i + 1] = 't';
                i++;
            }
        }
        System.out.println(a);
        int index = 0;
        int sum = 0;
        while (index < s.length()) {
            if (a[index] == 't') {
                index += 2;
                sum += 2;
                ans = Math.max(sum, ans);
            } else {
                //ans = Math.max(sum, ans);
                sum = 0;
                index++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        kuohao kuohao = new kuohao();
        String s = ")()())";
        System.out.println(kuohao.longestValidParentheses(s));
    }
}

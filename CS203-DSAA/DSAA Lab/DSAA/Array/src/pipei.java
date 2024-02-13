public class pipei {

    public static void main(String[] args) {
        String needle = "sad";
        String haystack = "sadbutsad";
        pipei pipei = new pipei();
        System.out.println(pipei.strStr(haystack, needle));
    }

    public int strStr(String haystack, String needle) {
        int[] D = new int[needle.length()];
        char[] p = needle.toCharArray();
        int nP = needle.length();
        for (int i = 0; i < nP; i++) {
            D[i] = 0;
        }
        int i = 1, j = 0;
        while (i < nP) {
            if (j < nP && i < nP && p[i] == p[j]) {
                j++;
                D[i] = j;
                i++;
            } else {
                if (j > 0) {
                    j = D[j - 1];
                } else i++;
            }
        }
        int ans = -1;
        int nT = haystack.length();
        char[] t = haystack.toCharArray();
        int k = 0, l = 0;
        while (k < nT) {
            if (l < nP && k < nT && t[k] == p[l]) {
                k++;
                l++;
                if (l == nP) {
                    ans = k - l;
                    return ans;
                }
            } else {
                if (l > 0) {
                    l = D[l - 1];
                } else k++;
            }
        }
        return -1;
    }
}

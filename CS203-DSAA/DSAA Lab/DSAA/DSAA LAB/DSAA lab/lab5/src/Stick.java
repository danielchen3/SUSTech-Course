import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Stick {

    public static long[] s1_hash = new long[1000001];

    public static long[] s1_hash_temp = new long[1000001];

    public static long[] s2_hash = new long[1000001];

    public static long[] s2_hash_temp = new long[1000001];

    public static void main(String[] args) {
        QReader input = new QReader();
        QWriter output = new QWriter();

        String s1 = input.next();
        String s2 = input.next();

        int ns1 = s1.length();
        int ns2 = s2.length();


        int left = 0, right = 100001;
        int midd = 0;

        if (ns1 == 1 && ns2 == 1) {
            if (s1.charAt(0) == s2.charAt(0)) output.println(1);
            else output.println(0);
        } else {
            while (left < right) {
                int mid = (left + right) / 2 + 1;
                hash_s1(s1, mid);
                hash_s2(s2, mid);
                MergeSort_s1(0, ns1 - mid);
                MergeSort_s2(0, ns2 - mid);

                if (check_is_equal(ns1 - mid, ns2 - mid)) {
                    midd = Math.max(mid, midd);
                    left = mid;
                } else right = mid - 1;
            }
            output.println(midd);
        }
        output.close();
    }

    public static boolean check_is_equal(int k1, int k2) {
        boolean judge = false;
        if (k1 >= k2) {
            for (int i = 0; i <= k2; i++) {
                int left = 0;
                int right = k1;
                while (left <= right) {
                    int mid = (left + right) / 2;
                    if (s1_hash[mid] == s2_hash[i]) {
                        judge = true;
                        return judge;
                    }
                    if (s1_hash[mid] < s2_hash[i]) {
                        left = mid + 1;
                    } else right = mid - 1;
                }
            }
        } else {
            for (int i = 0; i <= k1; i++) {
                int left = 0;
                int right = k2;
                while (left <= right) {
                    int mid = (left + right) / 2;
                    if (s2_hash[mid] == s1_hash[i]) {
                        judge = true;
                        return judge;
                    }
                    if (s2_hash[mid] < s1_hash[i]) {
                        left = mid + 1;
                    } else right = mid - 1;
                }
            }
        }
        return judge;
    }


    public static void hash_s1(String s, int k) {
        int n = s.length();
        Arrays.fill(s1_hash, 0);
        for (int i = 0; i < n - k + 1; i++) {
            if (i == 0) {
                for (int j = 0; j < k; j++)
                    s1_hash[i] += (long) s.charAt(j) * fake_math_pow(k - j - 1);
            } else {
                s1_hash[i] = (s1_hash[i - 1] - (long) s.charAt(i - 1) * fake_math_pow(k - 1)) * 97 + (long) s.charAt(i + k - 1);
            }
        }
    }

    public static void hash_s2(String s, int k) {
        int n = s.length();
        Arrays.fill(s2_hash, 0);
        for (int i = 0; i < n - k + 1; i++) {
            if (i == 0) {
                for (int j = 0; j < k; j++) {
                    s2_hash[i] += (long) s.charAt(j) * fake_math_pow(k - j - 1);
                }
            } else {
                s2_hash[i] = (s2_hash[i - 1] - (long) s.charAt(i - 1) * fake_math_pow(k - 1)) * 97 + (long) s.charAt(i + k - 1);
            }
        }
    }

    protected static void MergeSort_s1(int left, int right) {
        if (left >= right) return;
        int mid = (left + right) / 2;
        MergeSort_s1(left, mid);
        MergeSort_s1(mid + 1, right);
        merge_s1(left, mid, right);
    }

    protected static void merge_s1(int left, int mid, int right) {
        int leftpos = left;
        int rightpos = mid + 1;
        int temppos = left;

        //分成两组，并且按照大小顺序加入新的数组
        while (leftpos <= mid && rightpos <= right) {
            if (s1_hash[leftpos] < s1_hash[rightpos]) s1_hash_temp[temppos++] = s1_hash[leftpos++];
            else s1_hash_temp[temppos++] = s1_hash[rightpos++];
        }

        //把还没加进去的数字加进去
        while (leftpos <= mid) {
            s1_hash_temp[temppos++] = s1_hash[leftpos++];
        }
        while (rightpos <= right) {
            s1_hash_temp[temppos++] = s1_hash[rightpos++];
        }
        for (int i = left; i <= right; i++)
            s1_hash[i] = s1_hash_temp[i];
    }

    protected static void MergeSort_s2(int left, int right) {
        if (left >= right) return;
        int mid = (left + right) / 2;
        MergeSort_s2(left, mid);
        MergeSort_s2(mid + 1, right);
        merge_s2(left, mid, right);
    }

    protected static void merge_s2(int left, int mid, int right) {
        int leftpos = left;
        int rightpos = mid + 1;
        int temppos = left;

        //分成两组，并且按照大小顺序加入新的数组
        while (leftpos <= mid && rightpos <= right) {
            if (s2_hash[leftpos] < s2_hash[rightpos]) s2_hash_temp[temppos++] = s2_hash[leftpos++];
            else s2_hash_temp[temppos++] = s2_hash[rightpos++];
        }

        //把还没加进去的数字加进去
        while (leftpos <= mid) {
            s2_hash_temp[temppos++] = s2_hash[leftpos++];
        }
        while (rightpos <= right) {
            s2_hash_temp[temppos++] = s2_hash[rightpos++];
        }
        for (int i = left; i <= right; i++)
            s2_hash[i] = s2_hash_temp[i];
    }

    public static long fake_math_pow(int n) {
        long ans = 1;
        for (int i = 0; i < n; i++)
            ans *= (long) 97;
        return ans;
    }

}

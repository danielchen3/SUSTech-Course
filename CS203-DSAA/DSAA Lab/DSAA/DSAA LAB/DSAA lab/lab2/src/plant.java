import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class plant {


    public static int[] sub = new int[10000000];

    public static int[] temp = new int[10000000];

    public static int[] hh = new int[10000000];

    public static int[] temps = new int[10000000];

    public static int[] h = new int[10000000];

    public static int[] s = new int[10000000];

    public static int[] flag = new int[10000000];


    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        int p = in.nextInt();
        int q = in.nextInt();
        long ans = 0;
        long find_max = (long) -1e20;
        if (q >= 2) {
            for (int i = 0; i < n; i++) {
                h[i] = in.nextInt();
                s[i] = in.nextInt();
                sub[i] = h[i] - s[i];
                ans += s[i];
            }
            MergeSort(0, n - 1);
            int cnt = 0;
            for (int i = n - 1; i >= 0; i--) {
                if (sub[i] > 0 && cnt <= q - 1) {
                    cnt++;
                    ans += (long) sub[i];
                    flag[i] = 1;
                } else {
                    break;
                }
            }
            long max = ans;
            boolean jud = false;
            for (int i = 0; i < n; i++) {
                if (flag[i] == 0) {
                    flag[i] = 1;
                    long tmp = (long) sub[i] + ((long) Math.pow(2, p) - (long) 1) * (long) h[i];
                    if (!jud) {
                        if (tmp > (long) 0) {
                            boolean gun = false;
                            if (cnt == q) {
                                ans -= (long) sub[n - q];
                                max -= (long) sub[n - q];
                                gun = true;
                            }
                            if (max + tmp > ans) {
                                ans = max + tmp;
                            }
                            if (gun)
                                max += (long) sub[n - q];
                        }
                        jud = true;
                    } else {
                        if (tmp > (long) 0) {
                            boolean gun = false;
                            if (cnt == q) {
                                max -= (long) sub[n - q];
                                gun = true;
                            }
                            if (max + tmp > ans) {
                                ans = max + tmp;
                            }
                            if (gun)
                                max += (long) sub[n - q];
                        }
                    }
                } else {
                    long tmp = ((long) Math.pow(2, p) - (long) 1) * (long) h[i];
                    if (tmp > (long) 0) {
                        if (max + tmp > ans) {
                            ans = max + tmp;
                        }
                    }
                }
            }
        } else if (q == 1) {
            for (int i = 0; i < n; i++) {
                h[i] = in.nextInt();
                s[i] = in.nextInt();
                sub[i] = h[i] - s[i];
                ans += s[i];
                long tmp = (long) sub[i] + ((long) Math.pow(2, p) - (long) 1) * (long) h[i];
                if (i == 0) {
                    find_max = tmp;
                } else {
                    if (find_max < tmp) {
                        find_max = tmp;
                    }
                }
            }
            if (find_max > 0)
                ans += find_max;
        } else {
            for (int i = 0; i < n; i++) {
                h[i] = in.nextInt();
                s[i] = in.nextInt();
                sub[i] = h[i] - s[i];
                ans += s[i];
            }
        }
        out.println(ans);
        out.close();
    }

    protected static void MergeSort(int left, int right) {
        if (left >= right) return;
        int mid = (left + right) / 2;
        MergeSort(left, mid);
        MergeSort(mid + 1, right);
        merge(left, mid, right);
    }

    protected static void merge(int left, int mid, int right) {
        int leftpos = left;
        int rightpos = mid + 1;
        int temppos = left;

        //分成两组，并且按照大小顺序加入新的数组
        while (leftpos <= mid && rightpos <= right) {
            if (sub[leftpos] < sub[rightpos]) {
                temp[temppos] = sub[leftpos];
                hh[temppos] = h[leftpos];
                temps[temppos] = s[leftpos];
                temppos++;
                leftpos++;
            } else {
                temp[temppos] = sub[rightpos];
                hh[temppos] = h[rightpos];
                temps[temppos] = s[rightpos];
                temppos++;
                rightpos++;
            }
        }

        //把还没加进去的数字加进去
        while (leftpos <= mid) {
            temp[temppos] = sub[leftpos];
            hh[temppos] = h[leftpos];
            temps[temppos] = s[leftpos];
            temppos++;
            leftpos++;
        }
        while (rightpos <= right) {
            temp[temppos] = sub[rightpos];
            hh[temppos] = h[rightpos];
            temps[temppos] = s[rightpos];
            temppos++;
            rightpos++;
        }
        for (int i = left; i <= right; i++) {
            sub[i] = temp[i];
            h[i] = hh[i];
            s[i] = temps[i];
        }
    }
}


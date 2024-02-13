public class Main {
    public static int[] solve(int n) {
        int[] x = new int[n];
        int[] ans = new int[n];
        int flag = 0, new_flag = 0;
        for (int i = 0; i < n; i += 3) {
            ans[i] = Judge.query(i, i + 1, i + 2);
            if (i != 0) {
                if (ans[i] != ans[i - 3]) {
                    flag = i - 3;
                    break;
                }
            }
        }
        for (int i = flag + 3; i < n; i += 3) {
            ans[i] = Judge.query(i, i + 1, i + 2);
        }
        ans[flag + 1] = Judge.query(flag + 1, flag + 2, flag + 3);
        ans[flag + 2] = Judge.query(flag + 2, flag + 3, flag + 4);
        if (ans[flag + 1] != ans[flag]) {
            ans[flag + 3] = ans[flag + 1];
            new_flag = flag;
        } else if (ans[flag + 1] != ans[flag + 2]) {
            new_flag = flag + 1;
            ans[flag + 4] = ans[flag + 2];
        } else {
            new_flag = flag + 2;
            ans[flag + 5] = ans[flag + 3];
        }
        x[new_flag] = ans[new_flag];
        x[new_flag + 3] = ans[new_flag + 3];
        for (int i = 0; i < n; i += 3) {
            if (i == flag || i == flag + 3) continue;
            else {
                x[i] = Judge.query(new_flag, new_flag + 3, i);
                if (x[i] == 0 && ans[i] == 1) x[i + 1] = x[i + 2] = 1;
                else if (x[i] == 1 && ans[i] == 0) x[i + 1] = x[i + 2] = 0;
                else if (x[i] == 0 && ans[i] == 0) {
                    x[i + 1] = Judge.query(new_flag, new_flag + 3, i + 1);
                    if (x[i + 1] == 1) x[i + 2] = 0;
                    else x[i + 2] = Judge.query(new_flag, new_flag + 3, i + 2);
                } else if (x[i] == 1 && ans[i] == 1) {
                    x[i + 1] = Judge.query(new_flag, new_flag + 3, i + 1);
                    if (x[i + 1] == 0) x[i + 2] = 1;
                    else x[i + 2] = Judge.query(new_flag, new_flag + 3, i + 2);
                }
            }
        }
        if (flag == new_flag) {
            x[flag + 1] = Judge.query(new_flag, new_flag + 3, flag + 1);
            if (x[flag + 1] == 0) x[flag + 2] = 1;
            else x[flag + 2] = 0;
            x[flag + 4] = Judge.query(new_flag, new_flag + 3, flag + 4);
            if (x[flag + 3] == 0) {
                if (x[flag + 4] == 1 && ans[flag + 3] == 0) x[flag + 5] = 0;
                else if (x[flag + 4] == 1 && ans[flag + 3] == 1) x[flag + 5] = 1;
                else {
                    x[flag + 5] = Judge.query(new_flag, new_flag + 3, flag + 5);
                }
            } else if (x[flag + 3] == 1) {
                if (x[flag + 4] == 0 && ans[flag + 3] == 1) x[flag + 5] = 1;
                else if (x[flag + 4] == 0 && ans[flag + 3] == 0) x[flag + 5] = 0;
                else {
                    x[flag + 5] = Judge.query(new_flag, new_flag + 3, flag + 5);
                }
            }
        } else if (flag + 1 == new_flag) {
            x[flag + 2] = Judge.query(new_flag, new_flag + 3, flag + 2);
            if (x[flag + 2] == 0) x[flag + 3] = 1;
            else x[flag + 3] = 0;
            if (x[flag + 1] == 0) {
                if (x[flag + 2] == 1 && ans[flag] == 0) x[flag] = 0;
                else if (x[flag + 2] == 1 && ans[flag] == 1) x[flag] = 1;
                else {
                    x[flag] = Judge.query(new_flag, new_flag + 3, flag);
                }
            }
            if (x[flag + 1] == 1) {
                if (x[flag + 2] == 0 && ans[flag] == 1) x[flag] = 1;
                else if (x[flag + 2] == 0 && ans[flag] == 0) x[flag] = 0;
                else {
                    x[flag] = Judge.query(new_flag, new_flag + 3, flag);
                }
            }
            if (x[flag + 3] == 0) {
                if (x[flag + 4] == 1 && ans[flag + 3] == 1) x[flag + 5] = 1;
                else if (x[flag + 4] == 1 && ans[flag + 3] == 0) x[flag + 5] = 0;
                else {
                    x[flag + 5] = Judge.query(new_flag, new_flag + 3, flag + 5);
                }
            }
            if (x[flag + 3] == 1) {
                if (x[flag + 4] == 0 && ans[flag + 3] == 1) x[flag + 5] = 1;
                else if (x[flag + 4] == 0 && ans[flag + 3] == 0) x[flag + 5] = 0;
                else {
                    x[flag + 5] = Judge.query(new_flag, new_flag + 3, flag + 5);
                }
            }
        }
        else if(flag+2==new_flag){
            x[flag+3]=Judge.query(new_flag,new_flag+3,flag+3);
            if(x[flag+3]==0) x[flag+4]=1;
            else x[flag+4]=0;
            x[flag]=Judge.query(new_flag,new_flag+3,flag);
            if(x[flag]==0){
                if(x[flag+2]==1&&ans[flag]==0) x[flag+1]=0;
                else if(x[flag+2]==1&&ans[flag]==1) x[flag+1]=1;
                else{
                    x[flag+1]=Judge.query(new_flag,new_flag+3,flag+1);
                }
            }
            else if(x[flag]==1){
                if(x[flag+2]==0&&ans[flag]==1) x[flag+1]=1;
                else if(x[flag+2]==0&&ans[flag]==0) x[flag+1]=0;
                else{
                    x[flag+1]=Judge.query(new_flag,new_flag+3,flag+1);
                }
            }
        }
        return x;
    }
}

class Judge {
    private static int n;
    private static int[] x;
    private static int count = 0;

    public static int query(int a, int b, int c) {
        // check duplicated indices
        if (a == b || b == c || a == c) {
            System.err.printf("[!] Duplicated indices: %d %d %d\n", a, b, c);
            System.exit(1);
        }
        // check out-of-bound
        if (a >= n || b >= n || c >= n || a < 0 || b < 0 || c < 0) {
            System.err.printf("[!] Indices out of range: %d %d %d\n", a, b, c);
            System.exit(1);
        }

        count++;  // increase the guessing times

        int sum = x[a] + x[b] + x[c];  // count the number of 1s
        System.out.printf("[+] Query %d %d %d => %d\n", a, b, c, sum / 2);
        return sum / 2;  // if there are two or more 1s, it will return 1
    }

    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);

        System.out.print("[*] n: ");
        n = scanner.nextInt();

        // read an array x[]
        System.out.print("[*] x[]: ");
        x = new int[n];
        for (int i = 0; i < n; i++)
            x[i] = scanner.nextInt();

        // call your code
        int[] y = Main.solve(n);

        // compare your answer with the correct array
        boolean correct = true;
        for (int i = 0; i < n; i++)
            if (x[i] != y[i])
                correct = false;

        System.out.println();
        System.out.println(correct ? "[+] Correct" : "[-] Wrong");
        System.out.println("[+] Number of guesses: " + count);
        System.out.println("[+] Your answer: " + java.util.Arrays.toString(y));
        System.out.println("[+]   The array: " + java.util.Arrays.toString(x));
    }
}
/*int[] x = new int[n];
        int[] ans = new int[n];
        int flag = 0, new_flag = 0;
        for (int i = 0; i < n; i += 3) {
            ans[i] = Judge.query(i, i + 1, i + 2);
            if (i != 0) {
                if (ans[i] != ans[i - 3]) {
                    flag = i - 3;
                    break;
                }
            }
        }
        for (int i = flag + 3; i < n; i += 3) {
            ans[i] = Judge.query(i, i + 1, i + 2);
        }
        ans[flag + 1] = Judge.query(flag + 1, flag + 2, flag + 3);
        ans[flag + 2] = Judge.query(flag + 2, flag + 3, flag + 4);
        if (ans[flag + 1] != ans[flag]) {
            ans[flag + 3] = ans[flag + 1];
            new_flag = flag;
        } else if (ans[flag + 1] != ans[flag + 2]) {
            new_flag = flag + 1;
            ans[flag + 4] = ans[flag + 2];
        } else {
            new_flag = flag + 2;
            ans[flag + 5] = ans[flag + 3];
        }
        x[new_flag] = ans[new_flag];
        x[new_flag + 3] = ans[new_flag + 3];
        for (int i = 0; i < flag; i += 3) {
            x[i] = Judge.query(new_flag, new_flag + 3, i);
            if (x[i] == 0) {
                if (x[i] != ans[i]) {
                    x[i + 1] = x[i + 2] = 1;
                } else {
                    x[i + 1] = Judge.query(new_flag, new_flag + 3, i + 1);
                    if (x[i + 1] == 1) x[i + 2] = ans[i];
                    else {
                        x[i + 2] = Judge.query(new_flag, new_flag + 3, i + 2);
                    }
                }
            } else {
                if (x[i] != ans[i]) {
                    x[i + 1] = x[i + 2] = 0;
                } else {
                    x[i + 1] = Judge.query(new_flag, new_flag + 3, i + 1);
                    if (x[i + 1] == 0) x[i + 2] = ans[i];
                    else {
                        x[i + 2] = Judge.query(new_flag, new_flag + 3, i + 2);
                    }
                }
            }
        }
    }
        return x;
}*/
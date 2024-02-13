import java.util.Scanner;

public class redalert2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int m = input.nextInt();
        int k = input.nextInt();
        int ans = 0;
        int[][] flag = new int[103][103];
        for (int i = 0; i < m; i++) {
            int x = input.nextInt();
            int y = input.nextInt();
            desolator(n, x, y, flag);
        }
        for (int i = 0; i < k; i++) {
            int o = input.nextInt();
            int p = input.nextInt();
            cannon(n, o, p, flag);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (flag[i][j] == 0) ans++;
            }
        }
        System.out.println(ans);
    }

    public static void desolator(int bound, int x, int y, int[][] flag) {
        if (x - 1 >= 0 && x + 1 < bound && y - 1 >= 0 && y + 1 < bound) {
            flag[x - 1][y] = flag[x - 1][y - 1] = flag[x - 1][y + 1] = flag[x][y] = flag[x][y - 1] = flag[x][y + 1] = flag[x + 1][y + 1] = flag[x + 1][y] = flag[x + 1][y - 1] = 1;
        }
        if (x - 1 < 0) {
            if (y + 1 >= bound) {
                flag[x][y] = flag[x][y - 1] = flag[x + 1][y] = flag[x + 1][y - 1] = 1;
            } else if (y - 1 < 0) {
                flag[x][y] = flag[x][y + 1] = flag[x + 1][y + 1] = flag[x + 1][y] = 1;
            } else {
                flag[x][y] = flag[x][y - 1] = flag[x][y + 1] = flag[x + 1][y + 1] = flag[x + 1][y] = flag[x + 1][y - 1] = 1;
            }
        }
        if (x + 1 >= bound) {
            if (y + 1 >= bound) {
                flag[x - 1][y] = flag[x - 1][y - 1] = flag[x][y] = flag[x][y - 1] = 1;
            } else if (y - 1 < 0) {
                flag[x - 1][y] = flag[x - 1][y + 1] = flag[x][y] = flag[x][y + 1] = 1;
            } else {
                flag[x - 1][y] = flag[x - 1][y - 1] = flag[x - 1][y + 1] = flag[x][y] = flag[x][y - 1] = flag[x][y + 1] = 1;
            }
        }
        if (x - 1 >= 0 && x + 1 < bound) {
            if (y + 1 >= bound) {
                flag[x - 1][y] = flag[x - 1][y - 1] = flag[x][y] = flag[x][y - 1] = flag[x + 1][y] = flag[x + 1][y - 1] = 1;
            } else if (y - 1 < 0) {
                flag[x - 1][y] = flag[x - 1][y + 1] = flag[x][y] = flag[x][y + 1] = flag[x + 1][y + 1] = flag[x + 1][y] = 1;
            }
        }
    }

    public static void cannon(int bound, int x, int y, int[][] flag) {
        if (x - 2 >= 0 && x + 2 < bound && y - 2 >= 0 && y + 2 < bound) {
            flag[x - 2][y - 1] = flag[x - 2][y + 1] = flag[x - 1][y - 2] = flag[x - 1][y + 2] = flag[x + 1][y - 2] = flag[x + 1][y + 2] = flag[x + 2][y - 1] = flag[x + 2][y + 1] = flag[x - 2][y] = flag[x - 2][y - 2] = flag[x - 2][y + 2] = flag[x][y] = flag[x][y - 2] = flag[x][y + 2] = flag[x + 2][y + 2] = flag[x + 2][y] = flag[x + 2][y - 2] = 1;
        }
        if (x - 2 < 0) {
            if (x - 1 < 0) {
                if (y + 2 >= bound) {
                    if (y + 1 >= bound) {
                        flag[x + 1][y - 2] = flag[x + 2][y - 1] = flag[x][y] = flag[x][y - 2] = flag[x + 2][y] = flag[x + 2][y - 2] = 1;
                    } else {
                        flag[x + 1][y - 2] = flag[x + 2][y - 1] = flag[x + 2][y + 1] = flag[x][y] = flag[x][y - 2] = flag[x + 2][y] = flag[x + 2][y - 2] = 1;
                    }
                } else if (y - 2 < 0) {
                    if (y - 1 < 0) {
                        flag[x + 1][y + 2] = flag[x + 2][y + 1] = flag[x][y] = flag[x][y + 2] = flag[x + 2][y + 2] = flag[x + 2][y] = 1;
                    } else {
                        flag[x + 1][y + 2] = flag[x + 2][y - 1] = flag[x + 2][y + 1] = flag[x][y] = flag[x][y + 2] = flag[x + 2][y + 2] = flag[x + 2][y] = 1;
                    }
                } else {
                    flag[x + 1][y - 2] = flag[x + 1][y + 2] = flag[x + 2][y - 1] = flag[x + 2][y + 1] = flag[x][y] = flag[x][y - 2] = flag[x][y + 2] = flag[x + 2][y + 2] = flag[x + 2][y] = flag[x + 2][y - 2] = 1;
                }
            } else {
                if (y + 2 >= bound) {
                    if (y + 1 >= bound) {
                        flag[x - 1][y - 2] = flag[x + 1][y - 2] = flag[x + 2][y - 1] = flag[x][y] = flag[x][y - 2] = flag[x + 2][y] = flag[x + 2][y - 2] = 1;
                    } else {
                        flag[x - 1][y - 2] = flag[x + 1][y - 2] = flag[x + 2][y - 1] = flag[x + 2][y + 1] = flag[x][y] = flag[x][y - 2] = flag[x + 2][y] = flag[x + 2][y - 2] = 1;
                    }
                } else if (y - 2 < 0) {
                    if (y - 1 < 0) {
                        flag[x - 1][y + 2] = flag[x + 1][y + 2] = flag[x + 2][y + 1] = flag[x][y] = flag[x][y + 2] = flag[x + 2][y + 2] = flag[x + 2][y] = 1;
                    } else {
                        flag[x - 1][y + 2] = flag[x + 1][y + 2] = flag[x + 2][y - 1] = flag[x + 2][y + 1] = flag[x][y] = flag[x][y + 2] = flag[x + 2][y + 2] = flag[x + 2][y] = 1;
                    }
                } else {
                    flag[x - 1][y - 2] = flag[x - 1][y + 2] = flag[x + 1][y - 2] = flag[x + 1][y + 2] = flag[x + 2][y - 1] = flag[x + 2][y + 1] = flag[x][y] = flag[x][y - 2] = flag[x][y + 2] = flag[x + 2][y + 2] = flag[x + 2][y] = flag[x + 2][y - 2] = 1;
                }
            }
        }
        if (x + 2 >= bound) {
            if (x + 1 >= bound) {
                if (y + 2 >= bound) {
                    if (y + 1 >= bound) {
                        flag[x - 2][y - 1] = flag[x - 1][y - 2] = flag[x - 2][y] = flag[x - 2][y - 2] = flag[x][y] = flag[x][y - 2] = 1;
                    } else {
                        flag[x - 2][y - 1] = flag[x - 2][y + 1] = flag[x - 1][y - 2] = flag[x - 2][y] = flag[x - 2][y - 2] = flag[x][y] = flag[x][y - 2] = 1;
                    }
                } else if (y - 2 < 0) {
                    if (y - 1 < 0) {
                        flag[x - 2][y + 1] = flag[x - 1][y + 2] = flag[x - 2][y] = flag[x - 2][y + 2] = flag[x][y] = flag[x][y + 2] = 1;
                    } else {
                        flag[x - 2][y - 1] = flag[x - 2][y + 1] = flag[x - 1][y + 2] = flag[x - 2][y] = flag[x - 2][y + 2] = flag[x][y] = flag[x][y + 2] = 1;
                    }
                } else {
                    flag[x - 2][y - 1] = flag[x - 2][y + 1] = flag[x - 1][y - 2] = flag[x - 1][y + 2] = flag[x - 2][y] = flag[x - 2][y - 2] = flag[x - 2][y + 2] = flag[x][y] = flag[x][y - 2] = flag[x][y + 2] = 1;
                }
            } else {
                if (y + 2 >= bound) {
                    if (y + 1 >= bound) {
                        flag[x - 2][y - 1] = flag[x - 1][y - 2] = flag[x + 1][y - 2] = flag[x - 2][y] = flag[x - 2][y - 2] = flag[x][y] = flag[x][y - 2] = 1;
                    } else {
                        flag[x - 2][y - 1] = flag[x - 2][y + 1] = flag[x - 1][y - 2] = flag[x + 1][y - 2] = flag[x - 2][y] = flag[x - 2][y - 2] = flag[x][y] = flag[x][y - 2] = 1;
                    }
                } else if (y - 2 < 0) {
                    if (y - 1 < 0) {
                        flag[x - 2][y + 1] = flag[x - 1][y + 2] = flag[x + 1][y + 2] = flag[x - 2][y] = flag[x - 2][y + 2] = flag[x][y] = flag[x][y + 2] = 1;
                    } else {
                        flag[x - 2][y - 1] = flag[x - 2][y + 1] = flag[x - 1][y + 2] = flag[x + 1][y + 2] = flag[x - 2][y] = flag[x - 2][y + 2] = flag[x][y] = flag[x][y + 2] = 1;
                    }
                } else {
                    flag[x - 2][y - 1] = flag[x - 2][y + 1] = flag[x - 1][y - 2] = flag[x - 1][y + 2] = flag[x + 1][y - 2] = flag[x + 1][y + 2] = flag[x - 2][y] = flag[x - 2][y - 2] = flag[x - 2][y + 2] = flag[x][y] = flag[x][y - 2] = flag[x][y + 2] = 1;
                }
            }
        }
        if (x - 2 >= 0 && x + 2 < bound) {
            if (y + 2 >= bound) {
                if (y + 1 >= bound) {
                    flag[x - 2][y - 1] = flag[x - 1][y - 2] = flag[x + 1][y - 2] = flag[x + 2][y - 1] = flag[x - 2][y] = flag[x - 2][y - 2] = flag[x][y] = flag[x][y - 2] = flag[x + 2][y] = flag[x + 2][y - 2] = 1;
                } else {
                    flag[x - 2][y - 1] = flag[x - 2][y + 1] = flag[x - 1][y - 2] = flag[x + 1][y - 2] = flag[x + 2][y - 1] = flag[x + 2][y + 1] = flag[x - 2][y] = flag[x - 2][y - 2] = flag[x][y] = flag[x][y - 2] = flag[x + 2][y] = flag[x + 2][y - 2] = 1;
                }
            } if (y - 2 < 0) {
                if (y - 1 < 0) {
                    flag[x - 2][y + 1] = flag[x - 1][y + 2] = flag[x + 1][y + 2] = flag[x + 2][y + 1] = flag[x - 2][y] = flag[x - 2][y + 2] = flag[x][y] = flag[x][y + 2] = flag[x + 2][y + 2] = flag[x + 2][y] = 1;
                } else {
                    flag[x - 2][y - 1] = flag[x - 2][y + 1] = flag[x - 1][y + 2] = flag[x + 1][y + 2] = flag[x + 2][y - 1] = flag[x + 2][y + 1] = flag[x - 2][y] = flag[x - 2][y + 2] = flag[x][y] = flag[x][y + 2] = flag[x + 2][y + 2] = flag[x + 2][y] = 1;
                }
            }
        }
    }
}

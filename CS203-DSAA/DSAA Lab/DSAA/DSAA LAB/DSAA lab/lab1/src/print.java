import java.util.Scanner;

public class print {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int T = input.nextInt();
        for (int i = 0; i < T; i++) {
            int a = input.nextInt();
            int b = input.nextInt();
            int c = input.nextInt();
            int row_num = b * 2 + c * 2 + 1;
            int col_num = a * 2 + b * 2 + 1;

            char[][] res = new char[row_num][col_num];

            for (int j = 2 * b; j < row_num; j++) {
                for (int k = 0; k < 2 * a + 1; k++) {
                    if (j % 2 == 0) {
                        if (k % 2 == 0) res[j][k] = '+';
                        else res[j][k] = '-';
                    } else {
                        if (k % 2 == 0) res[j][k] = '|';
                        else res[j][k] = '.';
                    }
                }
            }

            for (int j = 0; j < 2 * b; j++) {
                for (int k = 0; k < 2 * a + 1; k++) {
                    if (j % 2 == 0) {
                        if (k % 2 == 0) res[j][k + (2 * b) - j] = '+';
                        else res[j][k + (2 * b) - j] = '-';
                    } else {
                        if (k % 2 == 0) res[j][k + (2 * b) - j] = '/';
                        else res[j][k + (2 * b) - j] = '.';
                    }
                }
            }

            for (int j = 0; j < 2 * c + 1; j++) {
                for (int k = col_num - 1; k > col_num - 1 - j; k--) {
                    if (k >= 0) {
                        if (j % 2 == 1 && res[j][k] == '\0') {
                            if (k % 2 == 0) res[j][k] = '|';
                            else res[j][k] = '/';
                        } else if (j % 2 == 0 && res[j][k] == '\0') {
                            if (k % 2 == 0) res[j][k] = '+';
                            else res[j][k] = '.';
                        }
                    }
                }
            }

            for (int j = 0; j < 2 * b; j++) {
                for (int k = j - 1; k >= 0; k--) {
                    if (j < 2 * c) {
                        if (j % 2 == 0 && res[row_num - 1 - j][2 * a + 1 + k] == '\0') {
                            if (k % 2 == 0) res[row_num - 1 - j][2 * a + 1 + k] = '.';
                            else res[row_num - 1 - j][2 * a + 1 + k] = '+';
                        }
                        if (j % 2 == 1 && res[row_num - 1 - j][2 * a + 1 + k] == '\0') {
                            if (k % 2 == 0) res[row_num - 1 - j][2 * a + 1 + k] = '/';
                            else res[row_num - 1 - j][2 * a + 1 + k] = '|';
                        }
                    } else {
                        if (res[row_num - 1 - j][2 * a + 1 + k] != '\0') break;
                        else {
                            if (j % 2 == 0 && res[row_num - 1 - j][2 * a + 1 + k] == '\0') {
                                if (k % 2 == 0) res[row_num - 1 - j][2 * a + 1 + k] = '.';
                                else res[row_num - 1 - j][2 * a + 1 + k] = '+';
                            }
                            if (j % 2 == 1 && res[row_num - 1 - j][2 * a + 1 + k] == '\0') {
                                if (k % 2 == 0) res[row_num - 1 - j][2 * a + 1 + k] = '/';
                                else res[row_num - 1 - j][2 * a + 1 + k] = '|';
                            }
                        }
                    }
                }
            }


            for (int j = 0; j < row_num; j++) {
                for (int k = 0; k < col_num; k++) {
                    if (res[j][k] == '\0') res[j][k] = '.';
                }
            }
            for (int j = 0; j < row_num; j++) {
                for (int k = 0; k < col_num; k++) {
                    System.out.printf("%c", res[j][k]);
                }
                System.out.println();
            }
        }
    }
}

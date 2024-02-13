import java.util.Scanner;
public class exercise4 {
    public static void main(String[] args) {
        int[][] a = {{1, 2, 3, 4}, {5, 6}, {7, 8, 9}, {10}};
        for (int row = 0; row < a.length; row++) {
            for (int column = 0; column < a[row].length; column++)
                System.out.printf("%d ", a[row][column]);
            System.out.println();
        }
    }
}



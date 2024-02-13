import java.util.Scanner;

class exercise1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int count = 0;
        while (count < n) {
            char result = 0;
            count++;
            for (int i = 0; i < 9; i++) {
                char s = input.next().charAt(0);
                if (i == 4) result = s;
            }
            System.out.printf("%c\n", result);
        }
    }
}
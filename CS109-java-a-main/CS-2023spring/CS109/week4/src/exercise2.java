import java.util.Scanner;

public class exercise2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int count = 0;
        double sum = 0;
        int total_credit = 0;
        while (count < n) {
            count++;
            int score = input.nextInt();
            int credit = input.nextInt();
            total_credit += credit;
            double GPA;
            if (score <= 100 && score >= 90) GPA = 4.0;
            else if (score < 90 && score >= 80) GPA = 3.0;
            else if (score < 80 && score >= 70) GPA = 2.0;
            else if (score < 70 && score >= 60) GPA = 1.0;
            else GPA = 0.0;
            sum += credit * GPA;
        }
        System.out.printf("%.2f\n", sum / total_credit);
    }
}

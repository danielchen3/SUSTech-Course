import java.util.Scanner;

public class exercise3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please input your credit and grade");
        double GPA = 0, sum_up = 0;
        int sum = 0;
        for (int i = 1; i < 10000; i++) {
            int grade, credit;
            String level;
            credit = sc.nextInt();
            if (credit == -1) {
                System.out.printf("You final GPA is %.2f\n", sum_up / sum);
                break;
            } else {
                grade = sc.nextInt();
                if (grade >= 96.5 && grade <= 100) level = "A+";
                else if (grade < 96.5 && grade >= 92.5) level = "A";
                else if (grade < 92.5 && grade >= 89.5) level = "A-";
                else if (grade < 89.5 && grade >= 86.5) level = "B+";
                else if (grade < 86.2 && grade >= 82.5) level = "B";
                else if (grade < 82.5 && grade >= 79.5) level = "B-";
                else level = "you are too weak, please do the course on the next semester";
            }
            switch (level) {
                case "A+":
                    GPA = 4.00;
                    break;
                case "A":
                    GPA = 3.94;
                    break;
                case "A-":
                    GPA = 3.85;
                    break;
                case "B+":
                    GPA = 3.73;
                    break;
                case "B":
                    GPA = 3.55;
                    break;
                case "B-":
                    GPA = 3.32;
                    break;
                default:
                    break;
            }
            sum_up += (double) GPA * credit;
            sum += credit;
        }
    }
}

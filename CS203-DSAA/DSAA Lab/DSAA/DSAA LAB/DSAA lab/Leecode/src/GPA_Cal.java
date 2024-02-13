import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class GPA_Cal {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Please input your credits and GPA:");

        int sum_credits = 0;
        double sum_grades = 0;

        while (true) {
            int credit = input.nextInt();
            if (credit == -1) break;
            double gpa = input.nextDouble();
            sum_credits += credit;
            sum_grades += (double) credit * gpa;
        }

        double final_gpa = sum_grades / sum_credits;

        System.out.println("Your final GPA is: " + final_gpa);

    }
}
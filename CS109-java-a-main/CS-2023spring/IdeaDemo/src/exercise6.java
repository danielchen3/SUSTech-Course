import java.util.Scanner;
public class exercise6 {
    public void displayMessage( String courseName ) {
        System.out.printf("Welcome to the Grade Book for the course %s !\n",courseName);
    }
    public static void main(String[] args) {
        exercise6 extra = new exercise6();
        extra.displayMessage("高等数学");
    }
}

import java.util.Scanner;
public class exercise7 {
    private String courseName;
    public void setcourseName(String Name){
        courseName=Name;
    }
    public String getcourseName(){
        return courseName;
    }
    public void displayMessage(){
        System.out.printf("Welcome to the Grade Book for %s\n",getcourseName());
    }
    public static void main(String[] args) {
        exercise7 Gradebook=new exercise7();
        String Your_class;
        Scanner input = new Scanner(System.in);
        System.out.printf("Please enter your course");
        Your_class=input.next();
        Gradebook.setcourseName(Your_class);
        System.out.println();
        Gradebook.displayMessage();
    }
}

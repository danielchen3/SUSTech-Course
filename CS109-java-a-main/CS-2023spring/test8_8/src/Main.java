import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
            try {
                File myObj = new File("Students.txt");
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    System.out.println(data);
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        //Student sta=new Student();
        Student students[] = {
                new Student(12210731, "chenchangxin", 90, 93, 99),
                new Student(12210729, "于东梅", 91, 90, 96),
                new Student(12210733, "陈来京", 99, 99, 99),
                new StudentXW(12210733, "陈来京", 99, 99, 99, "学习委员"),
                new StudentBZ(12210734, "Raul Yu", 90, 92, 87, "爬")
        };
        // Displaying the student data
        for (int i = 0; i < students.length; i++) {
            System.out.println(students[i].toString());
        }
    }
}

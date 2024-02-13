import java.io.FileWriter; // Import the FileWriter class
import java.io.IOException; // Import the IOException class to handle errors
public class WriteFile {
    public static void main(String[] args) {
        try {
            FileWriter myWriter = new FileWriter("Students.txt");
            myWriter.write("陈长信\n");
            myWriter.write("于来梅\n");
            myWriter.write("chenlaiijing\n");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
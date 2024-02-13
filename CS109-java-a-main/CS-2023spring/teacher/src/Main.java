import java.util.Scanner;
import java.io.*;
import java.io.FileReader;
import java.io.FileWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        int num;
        Scanner input = new Scanner(System.in);
        num = input.nextInt();
        FileWriter myWriter = new FileWriter("teachers.txt");
        for (int i = 0; i < num; i++) {
            String sub;
            int ID;
            ID = input.nextInt();
            sub = input.next();
            teacher teachers = new teacher(ID, sub);
            teachers.WriteFile(myWriter);
        }
        myWriter.close();
        //teacher.ReadFile();
        int ope = 0;
        while (ope == 0) {
            System.out.printf("请输入操作类型：");
            String s = input.next();
            if (s.trim().equals("cut")) {
                System.out.printf("请输入教师编号：");
                int ID = input.nextInt();
                teacher.cut_teacher(ID);
            } else if (s.trim().equals("find")) {
                System.out.printf("请输入教师编号：");
                int ID = input.nextInt();
                teacher T = teacher.find_teacher(ID);
                if (T == null) System.out.println("查无此人！");
                else System.out.printf("%d %s\n", T.get_ID(), T.get_sub());
            } else if (s.trim().equals("fresh")) {
                System.out.printf("请输入教师编号及新科目：");
                int ID = input.nextInt();
                String newsub = input.next();
                teacher.refresh_teacher(ID, newsub);
            } else if (s.trim().equals("-1")) {
                ope = 1;
            } else System.out.printf("错误！请重新输入！\n");
        }
        //teacher.output();
        FileWriter myWriternew = new FileWriter("teachers.txt");
        int flag = 0;
        teacher T = teacher.first_teacher;
        while (flag == 0) {
            if (T.next_teacher == null) {
                flag = 1;
                T.WriteFile(myWriternew);
            } else {
                T.WriteFile(myWriternew);
                T = T.next_teacher;
            }
        }
        myWriternew.close();
        teacher.ReadFile();
    }
}
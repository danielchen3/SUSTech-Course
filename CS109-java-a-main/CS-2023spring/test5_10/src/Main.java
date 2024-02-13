import java.util.Scanner;
import java.io.*;
import java.util.regex.Pattern;
import java.util.InputMismatchException;
public class Main {
    public static int ID,num;
    public static String name;
    public static int grade;
    public static void main(String[] args) throws IOException {
        System.out.print("请输入学生人数");
        Scanner input=new Scanner(System.in);
        num=input.nextInt();
        student [] students=new student[1000];
        FileWriter myWriter = new FileWriter("Students.txt");
        for(int i=0;i<num;i++){
            ID=input.nextInt();
            name=input.next();
            grade=input.nextInt();
            students[i]=new student(ID,name,grade);
            students[i].WriteFile(myWriter);
        }
        myWriter.close();
        student.ReadFile();
        System.out.printf("是否需要添加/删去学生");
        String ope;
        ope=input.next();
        FileWriter myWriternew=new FileWriter("Students.txt");
        if(ope.trim().equals("+")){
            System.out.printf("请输入增加的学生人数：");
            int add_num;
            add_num=input.nextInt();
            for(int i=num;i<num+add_num;i++){
                int newID=input.nextInt();
                String newname=input.next();
                int newgrade=input.nextInt();
                student.stu[i]=new student(newID,newname,newgrade);
            }
            //System.out.printf("%d %s %d",student.stu[1].getID(),student.stu[1].getname(),student.stu[1].getgrade());
            for(int i=0;i<num+add_num;i++){
                student.stu[i].WriteFile(myWriternew);
            }
            myWriternew.close();
        }
        else if(ope.trim().equals("-")){
            int cut_num;
            cut_num=input.nextInt();
            for(int i=0;i<cut_num;i++){
                System.out.printf("请输入删除学生的学号: ");
                int cutID;
                cutID=input.nextInt();

            }
        }//怎么回去？
    }
    public static void getinformation() {
        try {
            Scanner input = new Scanner(System.in);
            System.out.print("请输入学号：");
            int ID = input.nextInt();
            System.out.printf("请输入名字：");
            String name = input.next();
            System.out.print("请输入分数：");
            int grade = input.nextInt();
        }
        catch(InputMismatchException e){
            getinformation();
        }
    }
    public static void WriteToFile(){
        try {
            FileWriter myWriter = new FileWriter("Students.txt");
            myWriter.write("12210731,陈长信,99");
            myWriter.write("12210732,于冬梅,98");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
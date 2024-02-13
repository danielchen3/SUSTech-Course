import java.util.Scanner;

public class TestA {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        float gpa;
        int grade;
        System.out.println("请输入你的成绩：");
        grade=sc.nextInt();
        while(grade>100){
            System.out.println("请输入正确的成绩：");
            grade=sc.nextInt();
        }
        if(grade>=90){
            gpa=4.0f;
        }else if(grade>=80){
            gpa=3.0f;
        }else System.out.println("你该重修了");
    }
}

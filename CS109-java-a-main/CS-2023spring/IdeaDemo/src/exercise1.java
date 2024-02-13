import java.util.Scanner;

public class exercise1 {
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        Scanner input_string=new Scanner(System.in);
        String s;
        int Grade;
        System.out.print("学生姓名是：");
        s=input_string.nextLine();
        System.out.print("高等数学分数：");
        Grade=input.nextInt();
        System.out.printf("%s的等级成绩为：",s);
        if(Grade>100) System.out.println("你在扯淡吧！");
        else if(Grade>=97) System.out.println("A+,你太棒了");
        else if (Grade>=93) System.out.println("A");
        else if (Grade>=90) System.out.println("A-");
        else if (Grade>=87) System.out.println("B+");
        else if (Grade>=83) System.out.println("B");
        else if (Grade>=80) System.out.println("B-");
        else System.out.println("You are out! Please fuck off!");
    }
}

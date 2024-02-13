import java.util.Scanner;
public class class2 {
    public static void main(String[] args) {
        //char grade='A';//一个字符，占位16位
        //%d替代int long short byte类型的参数
        //
        int n;
        System.out.print("请输入秒数");
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        int hour = n / 3600;
        int min = (n - hour * 3600) / 60;
        int second = n - hour * 3600 - min * 60;
        System.out.printf("%d秒共有%d小时%d分钟%d秒", n, hour, min, second);
    }
}

import java.util.ArrayList;
import java.util.List;

public class SQRT {

    public static List<Integer> splitIntoFourSquares(int n) {
        List<Integer> result = new ArrayList<>();

        // 遍历寻找四个正整数的平方和
        for (int a = 1; a <= Math.sqrt(n); a++) {
            for (int b = a; b <= Math.sqrt(n - a * a); b++) {
                for (int c = b; c <= Math.sqrt(n - a * a - b * b); c++) {
                    for (int d = c; d <= Math.sqrt(n - a * a - b * b - c * c); d++) {
                        if (a * a + b * b + c * c + d * d == n) {
                            //输出
                        }
                    }
                }
            }
        }
        return null; // 如果无法拆分为四个正整数的平方和，返回空值
    }

    public static void main(String[] args) {
        int num = 9998; // 你可以替换成任何你想要拆分的整数
        List<Integer> result = splitIntoFourSquares(num);

        if (result != null) {
            System.out.printf("%d 可以拆分为以下平方数的和：%s", num, result.toString());
        } else {
            System.out.println("无法拆分为四个正整数的平方和");
        }
    }
}

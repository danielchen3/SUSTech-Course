import java.sql.SQLOutput;
import java.util.Stack;

public class stack {
    public static void main(String[] args) {
        Stack<Integer> stack=new Stack<>();
        System.out.println(stack);
        stack.push(3);
        stack.push(4);
        System.out.println(stack);
        System.out.println(stack.pop());
        System.out.println(stack);
        System.out.println(stack.isEmpty());
    }
}

class StackDemo{


}

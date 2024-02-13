import java.util.*;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

public class Preparation {
    public static void main(String[] args) {
        PriorityQueue<Integer> queue2 = new PriorityQueue<>();
        Stack<Integer> stack1 = new Stack<>();
        Queue<Integer> queue1 = new LinkedList<>();

        ArrayList<Integer> list1 = new ArrayList<>();

        Collections.sort(list1);



        for (int i = 100; i > 0; i--)
            queue1.add(i);
        queue1.add(2);


        System.out.println(queue1.peek());

    }
}

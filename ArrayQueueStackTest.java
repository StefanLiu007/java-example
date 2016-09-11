import java.util.ArrayDeque;

/**
 * Created by Stefan on 2016/9/8.
 */
public class ArrayQueueStackTest {
    public static void main(String[] args){
        test1();
        //test2();队列
    }

    private static void test2() {
        ArrayDeque queue = new ArrayDeque();
        queue.offer("疯狂的java讲义");
        queue.offer("轻量级javaee企业级应用实践");
        queue.offer("疯狂的android讲义");
        System.out.println(queue);
        System.out.println(queue.peek());
        System.out.println(queue);
        System.out.println(queue.poll());
        System.out.println(queue);
    }

    private static void test1() {
        ArrayDeque stack = new ArrayDeque();
        stack.push("f疯狂的java讲义");
        stack.push("轻量级javaee企业级应用实战");
        stack.push("疯狂的android讲义");
        System.out.println(stack);
        System.out.println(stack.peek());
        System.out.println(stack);
        System.out.println(stack.pop());
        System.out.println(stack);
    }
}

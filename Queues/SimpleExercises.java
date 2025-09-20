import java.util.*;
// Reverse and Generate Binary numbers

public class SimpleExercises {

    //1. Method to reverse any queue
    static <E> void reverseQueue(Queue<E> q) {
        Stack<E> stack = new Stack<>();
        // Move all elements to the stack
        while (!q.isEmpty()) {
            stack.push(q.remove());
        }
        // Pop them back to the queue
        while (!stack.isEmpty()) {
            q.add(stack.pop());
        }
    }

    //2. Generate Binary Numbers from 1 to n
    static void generateBinaryNumbers(int n){
        Queue<String> q = new LinkedList<>();
        q. add("1");
        for (int i = 1; i<= n; i++){
            String current = q.remove();
            System.out.println(current);
            //enqueue next two binary numbers
            q.add(current + "0");
            q.add(current + "1");
        }
    }

    public static void main(String[] args) {
        //Reversing queue
        Queue<Integer> queue = new LinkedList<>();
        queue.add(10);
        queue.add(20);
        queue.add(30);
        queue.add(40);

        System.out.println("Original queue: " + queue);
        reverseQueue(queue);
        System.out.println("Reversed queue: " + queue);

        //printing the generated binary numbers
        int n = 10;
        System.out.println("Binary nums from 1 to " + n + ":");
        generateBinaryNumbers(n);
    }
}

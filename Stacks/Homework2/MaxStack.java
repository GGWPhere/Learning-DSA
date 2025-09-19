/**Problem Recap
 * push(x), pop(), max()
 *  must be O(1) and stay on O(n)
 */

/**Key idea: Two Stacks
 * mainStack- regular stack with all elements 
 * maxStack- keep track of current max at each level
 * push value onto mainStack
 *      if empty or maxStack.peek(), push x
 *      else push current max again or just push when it >= current max and compare when popping.
 */

import java.util.*;

public class MaxStack {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        Deque<Integer> stack = new ArrayDeque<>();
        Deque<Integer> maxStack = new ArrayDeque<>();

        for (int i = 0; i < x; i++) {
            String in = sc.next();
            switch (in) {
                case "push":
                    int y = sc.nextInt();
                    stack.push(y);
                    if (maxStack.isEmpty()) {
                        maxStack.push(y);
                    } else {
                        maxStack.push(Math.max(y, maxStack.peek()));
                    }
                    break;

                case "pop":
                    stack.pop();
                    maxStack.pop();
                    break;

                case "max":
                    System.out.println(maxStack.peek());
                    break;

                default:
                    
            }
        }
    }
}

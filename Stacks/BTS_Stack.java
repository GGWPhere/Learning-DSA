import java.util.*;
// Generic array-based stack with automatic resizing <E> "helps for it to work with any type"
class StackArray<E> {
    private Object[] arr;
    private int top;
    private int capacity;

    public StackArray(int size) {
        if (size < 1) size = 1;
        arr = new Object[size];
        capacity = size;
        top = -1;
    }

    public void push(E x) {
        if (top == capacity - 1) {
            resize(capacity * 2);
        }
        arr[++top] = x;
    }

    private void resize(int newCap) {
        Object[] newArr = new Object[newCap];
        for (int i = 0; i <= top; i++) newArr[i] = arr[i];
        arr = newArr;
        capacity = newCap;
    }

    @SuppressWarnings("unchecked")
    public E pop() {
        if (top == -1) throw new RuntimeException("Underflow - stack is empty");
        E item = (E) arr[top];
        arr[top--] = null; // help GC
        // shrink when 1/4 full (optional)
        if (top + 1 <= capacity / 4 && capacity > 1) {
            int newCap = Math.max(1, capacity / 2);
            resize(newCap);
        }
        return item;
    }

    @SuppressWarnings("unchecked")
    public E peek() {
        if (top == -1) throw new RuntimeException("Underflow - stack is empty");
        return (E) arr[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public int size() {
        return top + 1;
    }
}

// Public class name -> file name must match: StackDemo.java
public class BTS_Stack {

    // Step 2 exercise: Reverse a string using StackArray<Character>
    public static String reverseString(String s) {
        //create an empty stack
        StackArray<Character> st = new StackArray<>(Math.max(1, s.length()));
        for (char c : s.toCharArray()) st.push(c); //push every character of string onto the stack
        //while stack not empty, pop and append to new string.
        StringBuilder sb = new StringBuilder();
        while (!st.isEmpty()) sb.append(st.pop());
        return sb.toString();
    }

    // Helper: checks if two brackets match
    private static boolean matches(char open, char close) {
        return (open == '(' && close == ')')
            || (open == '[' && close == ']')
            || (open == '{' && close == '}');
    }

    // Step 3 exercise: Balanced parentheses/brackets checker
    // Ignores non-bracket characters (but you can modify to treat them differently)
    public static boolean isBalanced(String s) {
        //create empty stack of character
        StackArray<Character> st = new StackArray<>(Math.max(1, s.length()));
        for (char c : s.toCharArray()) { //scan string from left to right 
            if (c == '(' || c == '[' || c == '{') {
                st.push(c);
            } else if (c == ')' || c == ']' || c == '}') {
                if (st.isEmpty()) return false;
                char top = st.pop(); // not balanced
                if (!matches(top, c)) return false; //also not balanced
            }
            // else: ignore other characters
        }
        return st.isEmpty();
    }

    // ---------- 1. INFIX -> POSTFIX -------------
    public static String infixToPostfix(String infix) {
        StringBuilder output = new StringBuilder();
        Stack<Character> ops = new Stack<>();
        String tokens = infix.replaceAll("\\s+", ""); // remove spaces

        for (int i = 0; i < tokens.length(); i++) {
            char ch = tokens.charAt(i);

            if (Character.isDigit(ch)) {
                // if digit, add to output
                output.append(ch).append(' ');
            }
            else if (ch == '(') {
                ops.push(ch);
            }
            else if (ch == ')') {
                // pop until '('
                while (!ops.isEmpty() && ops.peek() != '(') {
                    output.append(ops.pop()).append(' ');
                }
                if (!ops.isEmpty() && ops.peek() == '(') ops.pop(); // discard '('
            }
            else if (isOperator(ch)) {
                // pop higher/equal precedence operators
                while (!ops.isEmpty() && precedence(ops.peek()) >= precedence(ch)) {
                    if (ops.peek() == '(') break;
                    output.append(ops.pop()).append(' ');
                }
                ops.push(ch);
            }
        }

        // pop any remaining operators
        while (!ops.isEmpty()) {
            output.append(ops.pop()).append(' ');
        }

        return output.toString().trim();
    }

    private static boolean isOperator(char c) {
        return c=='+' || c=='-' || c=='*' || c=='/';
    }

    private static int precedence(char c) {
        switch (c) {
            case '+': case '-': return 1;
            case '*': case '/': return 2;
            default: return -1;
        }
    }

    // ---------- 2. EVALUATE POSTFIX -------------
    public static int evaluatePostfix(String postfix) {
        Stack<Integer> stack = new Stack<>();
        String[] tokens = postfix.split("\\s+");

        for (String token : tokens) {
            if (token.matches("\\d+")) {          // number
                stack.push(Integer.parseInt(token));
            } else {                              // operator
                int b = stack.pop();              // right operand
                int a = stack.pop();              // left operand
                switch (token.charAt(0)) {
                    case '+': stack.push(a + b); break;
                    case '-': stack.push(a - b); break;
                    case '*': stack.push(a * b); break;
                    case '/': stack.push(a / b); break;
                }
            }
        }
        return stack.pop();
    }

    //is Palindrome method
    public static boolean isPalindromeIgnoreNonLetters(String s) {
        Stack<Character> stack = new Stack<>();
        StringBuilder filtered = new StringBuilder();

        // 1. Filter: keep only letters, to lower
        for (char c : s.toCharArray()) {
            if (Character.isLetter(c)) {
                char lower = Character.toLowerCase(c);
                filtered.append(lower);
                stack.push(lower);
            }
        }

        // 2. Compare forward vs reverse using the stack
        for (int i = 0; i < filtered.length(); i++) {
            char fromFront = filtered.charAt(i);
            char fromBack = stack.pop();   // pops in reverse order
            if (fromFront != fromBack) {
                return false;
            }
        }
        return true;
    }

    // Entry point - runs step-by-step demos
    public static void main(String[] args) {
      /*   System.out.println("=== Step 1: Basic StackArray operations ===");
        StackArray<Integer> s = new StackArray<>(2);
        s.push(10);
        s.push(20);
        System.out.println("peek() => " + s.peek()); // 20
        System.out.println("pop()  => " + s.pop());  // 20
        System.out.println("size() => " + s.size()); // 1
        s.push(30);
        System.out.println("pop()  => " + s.pop());  // 30
        System.out.println("pop()  => " + s.pop());  // 10
        System.out.println("isEmpty() => " + s.isEmpty()); // true

        System.out.println("\n=== Step 2: Reverse a string using stack ===");
        String example = "hello";
        System.out.println(example + " -> " + reverseString(example)); // olleh

        System.out.println("\n=== Step 3: Balanced parentheses tests ===");
        String[] tests = {
            "()[]{}", "([{}])", "([)]", "(((()", "a*(b+c) - {x/[y + (z)]}", ""
        };
        for (String t : tests) {
            System.out.printf("%-25s : %s%n", t, isBalanced(t));
        }
        */


        // Next exercises you can try:
        // - infixToPostfix conversion
        // - evaluate postfix expression
        String infix = "(3 + 4) * 2";
        String postfix = infixToPostfix(infix);
        System.out.println("Postfix: " + postfix);

        int result = evaluatePostfix(postfix);
        System.out.println("Result: " + result);
        // - use stack to check palindrome while ignoring non-letters
        String[] tests = {
            "A man, a plan, a canal: Panama",
            "Race car!",
            "Hello"
        };

        for (String t : tests) {
            System.out.println("\"" + t + "\" -> " + isPalindromeIgnoreNonLetters(t));
        }
    }
}


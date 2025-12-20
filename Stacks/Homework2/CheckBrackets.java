/**Key Idea
 * when opening brackets, push both the character and its index (1-based) onto the stack
 * when closing brackets, 
 *  if isempty() == true, close bracket has no match so output its index
 *      else pop the top of the stack, if its a mismatch -> output current index
 */
/**After scanning the whole string, 
 * If stack is not empty -> some opening bracket has no match
 * Output the index of the first unmatched opening bracket, position is stored with bottom-most unmatched bracket 
 * but because we process left to right, the first unmatched opening bracket is the top of stack after scanning, 
 * since we push in order and never pop it if it is unmatched).
 */

import java.util.*;
public class CheckBrackets{
    static class Bracket{
        char type;
        int position;
        Bracket(char type, int position){
            this.type = type;
            this.position = position;
        }
        boolean matches(char closing){
            return (type== '(' && closing == ')') ||
                    (type == '[' && closing == ']') ||
                    (type == '{' && closing == '}');
        }
    }
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        Deque<Bracket> stack = new ArrayDeque<>();
        for(int i = 0; i<text.length(); i++){
            char ch = text.charAt(i);
            int position = i+1;
            if(ch == '(' || ch == '[' || ch == '{'){
                stack.push(new Bracket(ch, position));
            }
            else if(ch == ')' || ch == ']' || ch == '}'){
                if(stack.isEmpty()){
                    System.out.println(position);
                    return;
                }
                Bracket top = stack.pop();
                if(!top.matches(ch)){
                    System.out.println(position);
                    return;
                }
            }
        }
        if(!stack.isEmpty()){
            System.out.println(stack.peek().position);
        }else{
            System.out.println("Succes");
        }
        scanner.close();
    }

}
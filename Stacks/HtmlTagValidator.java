//import java.util.*;

// Goal
/*Verify tags like <div>...</div> are corrrectly nested
 * when you see opening tag like <div> push it on stack
 * for closing tag as well, you pop and check if it matches
 */

 //steps
 /* Scan string for <tag> and </tag>
  * If its opening tage -> push tag name
  * if it's a closing tag -> pop and compare
  * at the end the stack must be empty
  */

  /* 
public class HtmlTagValidator {
    public static boolean isValid(String html) {
        Deque<String> stack = new ArrayDeque<>();
        // simple parser for demo; not full HTML spec
        int i = 0;
        while (i < html.length()) {
            if (html.charAt(i) == '<') {
                int j = html.indexOf('>', i);
                if (j == -1) return false; // no closing '>'
                String tag = html.substring(i + 1, j);
                if (!tag.startsWith("/")) {         // opening
                    stack.push(tag);
                } else {                            // closing
                    if (stack.isEmpty() || !stack.pop().equals(tag.substring(1))) {
                        return false;
                    }
                }
                i = j + 1;
            } else i++;
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(isValid("<body><h1>Hello</h1></body>")); // true
        System.out.println(isValid("<body><h1>Hello</body></h1>")); // false
    }
}
*/
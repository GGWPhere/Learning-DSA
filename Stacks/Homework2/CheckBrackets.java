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

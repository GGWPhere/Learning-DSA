import java.util.Stack;

/*Key Idea
 * Stack is LIFO so we gotta reverse the order once
 * enqueue: Move elements between stacks during enqueue so dequeue is O(1)
 * Dequeue: Push onto one Stack directly
 */
/*StackIn: All new elements pushed here
 *StackOut: Elements ready to be dequeued
 * On dequeue or peek, if Stackout is empty, pop all from StackIn and push into StackOut
 * So oldest element is on top of the StackOut
 */

public class BTS_Queues_Stack<E>{
    private Stack<E> stackIn = new Stack<>();
    private Stack<E> stackOut = new Stack<>();   

    public void enqueue(E item){
        stackIn.push(item);
    }
    //Dequeue element
    public E dequeue(){
        if (isEmpty()) throw new IllegalStateException("Queue is empty");
        shiftStacks();
        return stackOut.pop();
    }

    public E peek(){
        if(isEmpty()) throw new IllegalStateException("Queue is empty");
        shiftStacks();
        return stackOut.peek();
    }
    
    public boolean isEmpty(){
        return stackIn.isEmpty() && stackOut.isEmpty();
    }

    private void shiftStacks(){
        //Only move if stackOut is empty
        if(stackOut.isEmpty()){
            while (!stackIn.isEmpty()){
                stackOut.push(stackIn.pop());
            }
        }
    }

    public static void main(String[] args) {
        BTS_Queues_Stack<Integer> q = new BTS_Queues_Stack<>();
        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);
        System.out.println("Peek: " + q.peek());    // -> 10
        System.out.println("Dequeue: " + q.dequeue()); // -> 10
        q.enqueue(40);
        System.out.println("Dequeue: " + q.dequeue()); // -> 20
        System.out.println("Dequeue: " + q.dequeue()); // -> 30
        System.out.println("Peek: " + q.peek());    // -> 40
    }
}
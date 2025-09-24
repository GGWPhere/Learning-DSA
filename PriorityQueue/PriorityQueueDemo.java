/*Each element has a priority and the highest (the smallest value) is removed first
 *offer()/add() -> insert element
 *poll() -> remove and return smallest element
 *peek() -> look at smallest element
 */

import java.util.PriorityQueue;
public class PriorityQueueDemo {
    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // Insert elements
        pq.add(10);
        pq.add(5);
        pq.add(20);

        System.out.println("Peek (smallest): " + pq.peek()); // 5

        // Remove elements in priority order
        while (!pq.isEmpty()) {
            System.out.println(pq.poll());
    }
    
    }
}

/* Understanding internals of queues using Arrays
 * front -> index of elemets to be removed
 * rear -> index of next empty slot for insertion
 * size -> number of elements currently in queue
 */
//will use a circular array

 public class BTS_Queues {

    // ---------- Queue implementation ----------
    static class MyQueue {
        private int[] arr;      // array to store queue elements
        private int front;      // index of the front element
        private int rear;       // index of next insertion
        private int size;       // current number of elements
        private int capacity;   // max capacity of the queue

        public MyQueue(int capacity) {
            this.capacity = capacity;
            arr = new int[capacity];
            front = 0;
            rear = 0;
            size = 0;
        }

        // Add an element at the rear
        public void enqueue(int value) {
            if (isFull()) {
                throw new IllegalStateException("Queue is full");
            }
            arr[rear] = value;
            rear = (rear + 1) % capacity; // circular increment
            size++;
        }

        // Remove an element from the front
        public int dequeue() {
            if (isEmpty()) {
                throw new IllegalStateException("Queue is empty");
            }
            int value = arr[front];
            front = (front + 1) % capacity; // circular increment
            size--;
            return value;
        }

        // View the front element without removing it
        public int peek() {
            if (isEmpty()) {
                throw new IllegalStateException("Queue is empty");
            }
            return arr[front];
        }

        public boolean isEmpty() { return size == 0; }
        public boolean isFull()  { return size == capacity; }
        public int size()        { return size; }
    }

    // ---------- Demo ----------
    public static void main(String[] args) {
        MyQueue q = new MyQueue(5);

        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);
        System.out.println("Front: " + q.peek()); // -> 10

        System.out.println("Dequeued: " + q.dequeue()); // -> 10
        System.out.println("Dequeued: " + q.dequeue()); // -> 20

        q.enqueue(40);
        q.enqueue(50);
        q.enqueue(60); // rear wraps around here
        System.out.println("Front after wrap: " + q.peek()); // -> 30
        System.out.println("Queue size: " + q.size());       // -> 4
    }
}

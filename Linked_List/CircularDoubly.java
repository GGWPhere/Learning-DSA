public class CircularDoubly {

    static class Node {
        int data;
        Node next, prev;
        Node(int data) { this.data = data; }
    }

    static class CircularDoublyLinkedList {
        Node head = null;
        Node tail = null;

        // Insert at end
        void insertAtEnd(int data) {
            Node newNode = new Node(data);
            if (head == null) {
                head = tail = newNode;
                head.next = head;
                head.prev = head;
            } else {
                newNode.prev = tail;
                newNode.next = head;
                tail.next = newNode;
                head.prev = newNode;
                tail = newNode;
            }
        }

        // Insert at beginning
        void insertAtBeginning(int data) {
            Node newNode = new Node(data);
            if (head == null) {
                head = tail = newNode;
                head.next = head;
                head.prev = head;
            } else {
                newNode.next = head;
                newNode.prev = tail;
                head.prev = newNode;
                tail.next = newNode;
                head = newNode;
            }
        }

        //delete function
        void delete(int key) {
            if (head == null) return; // empty list
            Node current = head;
            // traverse once around the circle
            do{
                if (current.data == key) {
                    // Single node
                    if (current == head && current == tail) {
                        head = null;
                        tail = null;
                    }else if (current == head) { //delete head
                        head = head.next;
                        tail.next = head;
                        head.prev = tail;
                    }else if (current == tail) { //delete tail
                        tail = tail.prev;
                        tail.next = head;
                        head.prev = tail;
                    }else { //delete middle
                        current.prev.next = current.next;
                        current.next.prev = current.prev;
                    }
                    return; // deletion done
                }
                current = current.next;
            } while(current != head);
        }

        //search 
        boolean search(int key){
            if(head == null) return false;
            Node current = head;
            do {
                if (current.data == key){
                    return true;
                }
                current = current.next;
            }while (current!= head);
            return false;
        }

        // Forward traversal (one full circle)
        void printForward() {
            if (head == null) { System.out.println("(empty)"); return; }
            Node temp = head;
            do {
                System.out.print("[" + temp.data + "]");
                temp = temp.next;
                if (temp != head) System.out.print(" <-> ");
            } while (temp != head);
            System.out.println(" -> (back to head)");
        }

        // Backward traversal (one full circle)
        void printBackward() {
            if (tail == null) { System.out.println("(empty)"); return; }
            Node temp = tail;
            do {
                System.out.print("[" + temp.data + "]");
                temp = temp.prev;
                if (temp != tail) System.out.print(" <-> ");
            } while (temp != tail);
            System.out.println(" -> (back to tail)");
        }

        //sorting
        void sort() {
            if (head == null || head.next == head) return; // 0 or 1 node

            boolean swapped;
            do {
                swapped = false;
                Node current = head;

                do {
                    Node nextNode = current.next;
                    if (nextNode != head && current.data > nextNode.data) {
                        // swap the data
                        int temp = current.data;
                        current.data = nextNode.data;
                        nextNode.data = temp;
                        swapped = true;
                    }
                    current = current.next;
                } while (current.next != head); // loop until we are one step before head

            } while (swapped);
        }

        //reverse
        public void Reverse(){ //head become tail and tail become head
            if(head == null || head.next == head) return;
            Node current = head;
            do{
                //swap next andprev for each node
                Node temp = current.next;
                current.next = current.prev;
                current.prev = temp;
                //move to "next" node which is old prev
                current = temp;
            }while (current != head);
            Node temp = head;
            head = tail;
            tail = temp;
        }
    }

    public static void main(String[] args) {
        CircularDoublyLinkedList cdll = new CircularDoublyLinkedList();

        cdll.insertAtEnd(10);
        cdll.insertAtEnd(20);
        cdll.insertAtEnd(30);
        cdll.insertAtBeginning(5);

        System.out.println("Forward:");
        cdll.printForward();

        System.out.println("Backward:");
        cdll.printBackward();

        cdll.sort();
        cdll.Reverse();

        cdll.delete(5);   // delete head
        System.out.println("After deleting 5:");
        cdll.printForward();

        System.out.println("is 50 still here?: " + cdll.search(50));
        System.out.println("is 20 still here?: " + cdll.search(20));
    }
}

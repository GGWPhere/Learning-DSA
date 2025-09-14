public class Circular {

    static class Node {
        int data;
        Node next;
        Node(int data) { this.data = data; }
    }

    static class CircularLinkedList {
        Node head = null;
        Node tail = null; // keep tail for O(1) insert at end

        // Insert at end
        void insertAtEnd(int data) {
            Node newNode = new Node(data);
            if (head == null) {
                head = newNode;
                tail = newNode;
                newNode.next = head; // circular link to itself
            } else {
                tail.next = newNode;
                newNode.next = head;
                tail = newNode;
            }
        }

        // Insert at beginning
        void insertAtBeginning(int data) {
            Node newNode = new Node(data);
            if (head == null) {
                head = newNode;
                tail = newNode;
                newNode.next = head;
            } else {
                newNode.next = head;
                head = newNode;
                tail.next = head; // keep it circular
            }
        }

        //delete Node
        public void delete(int key){
            if (head == null) return; 
            Node current = head;
            Node prev = tail;// tail points to head

            while(current != head){
                if(current.data == key){ //case of single node only
                    if (current ==head && current == tail){
                        head = null;
                        tail = null;
                    }else if (current == head){ //delete head
                        head = head.next;
                        tail.next = head;
                    }else if (current == tail){//delete tail
                        prev.next = head;
                        tail = prev;
                    }else{//delete middle node
                        prev.next = current.next;
                    }
                    return;
              }
              prev = current;
              current = current.next;
           }
        }

        //search, return true if key exists in the list
        boolean search(int key){
            if(head ==null) return false; //empty list
            Node current = head;
            while(current != head){
                if(current.data == key){
                    return true; //found the key
                }
                current = current.next; //stop when loop back to head
            }
            return false; //if not found 
        }

        // Print list once around the circle
        void printList() {
            if (head == null) {
                System.out.println("(empty)");
                return;
            }
            Node current = head;
            do {
                System.out.print("[" + current.data + "]");
                current = current.next;
                if (current != head) System.out.print(" -> ");
            } while (current != head);
            System.out.println(" -> (back to head)");
        }
    }

    public static void main(String[] args) {
        CircularLinkedList cll = new CircularLinkedList();
        cll.insertAtEnd(10);
        cll.insertAtEnd(20);
        cll.insertAtEnd(30);
        cll.insertAtBeginning(5);

        System.out.println("Original list");
        cll.printList();

        System.err.println("search 20: " + cll.search(20));
        System.out.println("search 99: " + cll.search(99));

        cll.delete(20);
        System.out.println("After delete 20: ");
        cll.printList();
    }
}

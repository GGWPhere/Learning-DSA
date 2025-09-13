/* 
// Node class
class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

// Singly Linked List class
class SinglyLinkedList {
    private Node head;

    public SinglyLinkedList() {
        this.head = null;
    }

    //Insert at head
    public void insertAtHead(int data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
    }

    //  Insert at tail
    public void insertAtTail(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            return;
        }
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }

    // Delete a node by value
    public void deleteNode(int key) {
        if (head == null) return;

        if (head.data == key) {
            head = head.next;
            return;
        }

        Node current = head;
        while (current.next != null && current.next.data != key) {
            current = current.next;
        }

        if (current.next != null) {
            current.next = current.next.next;
        }
    }

    //implement delete by position
    public void deleteByPosition(int position){
        //Case when list is empty
        if(head == null){
            System.out.println("List is empty");
            return;
        }

        //deleting the head
        if(position == 0 ){
            head = head.next;
            return;
        }
        Node current = head;
        int index = 0;

        //traverse until the node before the target
        while(current!= null && index < position-1){
            current = current.next;
            index++;
        }

        //if it is out of bounds
        if(current == null || current.next == null){
            System.out.println("It is out of bounds");
            return;
        }

        //delete the node between current and current.next
        current.next = current.next.next;
    }


    // 7 insert at position (in a 0-based index)
    public void insertATPosition(){
        Node newNode = new Node(data);

        //Case when at the beginning
        if(position == 0){
            newNode next = head;
            head = newNode;
            return;
        }
        Node current = head; 
        int index = 0;

        //traverse until the node before the target 
        while(current != null && index <= position-1){
            current = current.next;
            index++;
        }

        //if it is out of bounds
        if(current == null){
            System.out.println("It is out of bounds");
            return;
        }

        //insert new node between current and current.next
        newNode.next = current.next;
        current.next = newNode;

    }

        // Search for a value
    public boolean search(int key) {
        Node current = head;
        while (current != null) {
            if (current.data == key) return true;
            current = current.next;
        }
        return false;
    }

        //  Print the list
    public void printList() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    // Reverse the list
    public void reverse() {
        Node prev = null;
        Node current = head;
        Node next = null;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head = prev;
    }

    //detect loop/cycle in the list
    public boolean hasCycle(){
        if (head == null){
            return false;
        }
        Node slow = head;
        Node fast = head;

        //
        while(fast != null && fast.next != null){
            slow = slow.next;  //goes 1 step
            fast = fast.next.next;  //goes 2 step

            //if cycle is found
            if(slow == fast){
                return true;
            }
        }
        return false;
    }

    //implement sorted Insert
    public void sortedInsert(int data){
        Node newNode = new Node(data);

        //empty list or insert before current head
        if(head==null || data <= head.data){
            newNode.next = head;
            head = newNode;
            return;
        }

        Node current = head;
        //traverse until the correct one
        while(current.next != null && current.next.data < data){
            current = current.next;
        }
        
        //insert new node after current
        newNode.next = current.next;
        current.next = newNode;

    }
}

// Main class to test
public class Singly{
    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();

        list.insertAtHead(10);
        list.insertAtHead(20);
        list.insertAtTail(30);

        System.out.println("Original List:");
        list.printList(); // 20 -> 10 -> 30 -> null

        list.deleteNode(10);
        System.out.println("After deleting 10:");
        list.printList(); // 20 -> 30 -> null

        System.out.println("\nDelete node at position 1:");
        list.deleteByPosition(1); // deletes node with value 20
        list.printList();

        System.out.println("Search for 30: " + list.search(30)); // true
        System.out.println("Search for 40: " + list.search(40)); // false

        System.out.println("Has cycle?: " + list.hasCycle());

        list.reverse();
        System.out.println("Reversed List:");
        list.printList(); // 30 -> 20 -> null
    }
}
    */

/* 
class Node{
    int data;
    Node next;
    Node prev;

    Node(int data){
        this.data = data;
        next = null;
        prev = null;
    }
}

class DoublyLinkedList{
    Node head;

    //insert at head
    public void insertAtHead(int data){
        Node newNode = new Node(data);
        
        if(head == null){ 
            head = newNode;
            return;
        }
        newNode.next = head; //Link new node to current head
        head.prev = newNode;

        head = newNode; //head to point to new Node
        
    }

    //insert at Tail
    public void insertAtTail(int data){
        Node newNode = new Node(data);
        if(head == null){
            head = newNode;
            return;
        }
        Node current = head;
        //traverse till last node
        while(current.next != null){
            current = current.next;
        }

        current.next = newNode; //forward link
        newNode.prev = current; //backword link
    }

    public void insertATPosition(int data, int position){
        Node newNode = new Node(data);
        //insert at Head
        if(position == 0){
            newNode.next = head;
            if(head != null) {
                head.prev = newNode;
            }
            head = newNode;
            return;
        }
        Node current = head;
        int index = 0;

        //traverse to node before position
        while (current != null && index < position -1){
            current = current.next;
            index++;
        }
        //if position is beyond list length, insert at tail
        while(current == null || current.next ==null){
            insertAtTail(data);
            return;
        }
        //Adjust pointers to insert in the middle
        newNode.next = current.next;
        newNode.prev = current;
        current.next.prev = newNode;
        current.next = newNode;
    }
    
    //delete at position
    public void deleteAtPosition(int position){
        if (head == null) return; //empty list
        Node current = head; 
        //Delete head
        if (position == 0 ){
            head = head.next;
            if(head != null) head.prev =null;
            return;
        }
        int index = 0;
        //traverse to the node at position
        while (current != null && index < position-1){
            current = current.next;
            index++;
        }
        //position out of bounds
        if(current == null) return;
        //adjust pointers
        if(current.next != null) current.next.prev = current.prev;
        if(current.prev != null) current.prev.next = current.next;
        //detach current
        current.next = null;
        current.prev = null;
    }

    //reverse function
    public void reverse(){
        Node current = head;
        Node temp = null;
        while(current != null){
            //swap prev and next
            temp = current.prev;
            current.prev = current.next;
            current.next = temp;
            current = current.prev; //move to original next node (now prev)
        }
        //update head to the last node (temp.prev after loop)
        if(temp != null){
            head = temp.prev;
        }
    }

        // helper to print the list from head to tail
    public void printForward() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data);
            if (temp.next != null) System.out.print(" <-> ");
            temp = temp.next;
        }
        System.out.println();
    }

    //print backwards
    public void printBackwards(){
        if(head == null) return;
        Node current = head;

        //move to tail
        while (current.next != null){
            current = current.next;
        }
        //traverse backwards
        while (current != null){
            System.out.println("[" + current.data +"]");
            current = current.prev;
            if(current != null) System.out.println(" <-> ");
        }
        System.out.println();
    }

    //search feature (return the index of the first node whose data equals 'value')
    public int findFirst(int data){
        Node current = head;
        int index = 0;
        while(current != null){
            if (current.data == data){
                return index; //found
            }
            current = current.next;
            index++;
        }
        return -1; //not found
    }

    //quick boolean search
    public boolean contains(int data){
        return findFirst(data) != 1;
    }
    
    //sorted insertion
    void sortedInsert(int data) {
    Node newNode = new Node(data);
    //empty list or value is smallest
    if (head == null || head.data >= data) {
        newNode.next = head;
        if (head != null) head.prev = newNode;
        head = newNode;
        return;
    }
    Node current = head;
    // Move forward until we find a node whose data is >= value
    while (current.next != null && current.next.data < data) {
        current = current.next;
    }
    // Insert after 'current'
    newNode.next = current.next;
    newNode.prev = current;

    if (current.next != null) {
        current.next.prev = newNode;
    }
    current.next = newNode;
    }

    //implement merge
    static DoublyLinkedList mergeSorted(DoublyLinkedList a, DoublyLinkedList b) {
        Node head1 = a.head;
        Node head2 = b.head;

        DoublyLinkedList result = new DoublyLinkedList();
        Node tail = null; // last node in the merged list

        // While both lists have nodes
        while (head1 != null && head2 != null) {
            Node nodeToAdd;

            if (head1.data <= head2.data) {
                nodeToAdd = head1;
                head1 = head1.next;
            } else {
                nodeToAdd = head2;
                head2 = head2.next;
            }

            nodeToAdd.prev = tail;    // connect back
            nodeToAdd.next = null;    // detach from old list’s next (will reconnect)

            if (tail == null) {        // first node
                result.head = nodeToAdd;
            } else {
                tail.next = nodeToAdd;
            }
            tail = nodeToAdd;
        }

        // Append remaining nodes from whichever list is not empty
        Node remaining = (head1 != null) ? head1 : head2;
        while (remaining != null) {
            Node nodeToAdd = remaining;
            remaining = remaining.next;

            nodeToAdd.prev = tail;
            nodeToAdd.next = null;

            if (tail == null) {
                result.head = nodeToAdd;
            } else {
                tail.next = nodeToAdd;
            }
            tail = nodeToAdd;
        }

        return result;
    }
}

public class Doubly{
    public static void main(String[] args) {
       // DoublyLinkedList list = new DoublyLinkedList();
       /*  list.insertAtHead(10);
        list.insertAtHead(20);
        list.insertAtHead(30);
        list.printForward(); // Expected: 30 <-> 20 <-> 10
        
        list.insertAtTail(40);
        list.insertAtTail(50);
        list.printForward();

        list. insertATPosition(35, 3);
        list.printForward();


        list.deleteAtPosition(2);

        System.out.println("printing backwards: ");
        list.printBackwards();

        int pos = list.findFirst(30);
        if (pos != -1){
            System.out.println("found in " + pos);
        }else {
            System.out.println("30 not found");
        }
        System.out.println("List has 50? " + list.contains(50));

        list.reverse();
        System.out.println("Reversed list");
        list.printForward();
        */

        /*list.sortedInsert(30);
        list.sortedInsert(10);
        list.sortedInsert(40);
        list.sortedInsert(20);
        list.sortedInsert(25);
        System.out.println("List after sorted insert: ");
        list.printForward();
        */
/* 
        DoublyLinkedList listA = new DoublyLinkedList();
        listA.sortedInsert(10);
        listA.sortedInsert(20);
        listA.sortedInsert(40);

        DoublyLinkedList listB = new DoublyLinkedList();
        listB.sortedInsert(15);
        listB.sortedInsert(25);
        listB.sortedInsert(30);
        listB.sortedInsert(50);

        System.out.println("List A:");
        listA.printForward();

        System.out.println("List B:");
        listB.printForward();

        DoublyLinkedList merged = DoublyLinkedList.mergeSorted(listA, listB);
        System.out.println("Merged List:");
        merged.printForward();
    }
}
*/
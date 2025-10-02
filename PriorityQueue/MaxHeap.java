/*A complete binary tree (filled left to right) with heap property 
 *Parent >= children
 *Largest element is always root
 */
/*Parents at index 'i'
 *Left child at '2*i + 1'
 *Right child at '2*i + 2'
 *Parent of node 'i' is '(i-1)/2' (integer division)
 */
/*Insertion
 *Place new key at end of array (next free leaf)
 *Bubble up() (sift up/ heapify up):
 *  -While key > parent -> swap with parent
 */
/*Extract Max
 *Root (index 0) is max
 *Replcae root with last element in array
 *Bubble down (heapify down)
 *  -Compare new root with larger child
 *  -If smaller than child, swap and continue down.
 */
//Build HeapStart: from last non-leaf node and heapify down each node

public class MaxHeap {
    private int[] heap;
    private int size;
    private int capacity;

    public MaxHeap(int capacity){
        this.capacity = capacity;
        this.heap = new int[capacity];
        this.size = 0;
    }

    private int parent(int i){ return (i - 1) / 2; }
    private int left(int i){ return 2 * i + 1; }
    private int right(int i){ return 2 * i + 2; }

    private void swap(int i, int j) {
        int tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }

    // Insert a new value into the heap
    public void insert(int value) {
        if (size == capacity) {
            throw new IllegalStateException("Heap is full");
        }
        heap[size] = value;
        int current = size;
        size++;

        // Bubble up
        while (current > 0 && heap[current] > heap[parent(current)]) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    // Return max element
    public int getMax() {
        if (size == 0) throw new IllegalStateException("Heap is empty");
        return heap[0];
    }

    // Remove and return max element
    public int extractMax() {
        if (size == 0) throw new IllegalStateException("Heap is empty");

        int max = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapify(0);
        return max;
    }

    // Heapify down
    private void heapify(int i) {
        int largest = i;
        int l = left(i);
        int r = right(i);

        if (l < size && heap[l] > heap[largest]) {
            largest = l;
        }
        if (r < size && heap[r] > heap[largest]) {
            largest = r;
        }
        if (largest != i) {
            swap(i, largest);
            heapify(largest);
        }
    }

    public static void main(String[] args){
        MaxHeap maxHeap = new MaxHeap(10);
        maxHeap.insert(20);
        maxHeap.insert(15);
        maxHeap.insert(30);
        maxHeap.insert(30);
        maxHeap.insert(40);
        maxHeap.insert(10);
        System.out.println("Max element is:" + maxHeap.getMax());
        System.out.println("Extract max: " + maxHeap.extractMax());
        System.out.println("Max element after extraction: " + maxHeap.getMax());
        //extract all remaining elements
        System.out.println("elements in descending order: ");
        while (true) {
            try {
                System.out.println(maxHeap.extractMax() + " ");
            } catch (IllegalStateException e) {
                break; //heap is empty
            }
        }
    }
}

/*We'll use a ArrayList to store heap
 * Index: 0 1 2 3 4 ...
 * Value:[. . . . . ...]
 * parent(i) = (i-1)/2
 * left(i) = 2*i+1
 * right(i) = 2*i+2
 */ 
/*Operations
 * insert(x): add element and "bubble up" to keep min property  
 * extractMin(): remove and return smallest element(roOt)
 * peekMin(): Look at smallest element without removing
 * size(): number of elements.
 */
//later we can heapify
import java.util.Arrays;

public class MinHeap{
    public int[] heap;
    private int size;
    private int capacity;
    public MinHeap(int capacity){
        this.capacity = capacity;
        this.heap = new int[capacity];
        this.size = 0;
    }
    private int parent(int i){ return(i-1)/2;}
    private int left(int i){ return 2*i+1;}
    private int right(int i)  { return 2 * i + 2; }

    private void swap(int i, int j) {
        int tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }
    
    //insert
    public void insert(int value){
        if(size == capacity){
            throw new RuntimeException("Heap is full");
        }
        heap[size] = value; //put at end
        int i = size;
        size++;
        //bubble up
        while(i != 0 && heap[parent(i)] > heap[i]){
            swap(i, parent(i));
            i = parent(i);
        }
    }
    //peekMin
    public int peekMin(){
        if(size == 0) throw new RuntimeException("Heap is empty");
        return heap[0];
    }
    //extractMin
    public int extractMin(){
        if (size == 0) throw new RuntimeException("Heap is empty");
        int root = heap[0];
        heap[0] = heap[size-1];
        size--;
        heapifyDown(0);
        return root;
    }

    //sift down
    private void heapifyDown(int i) {
        int smallest = i;
        int l = left(i);
        int r = right(i);

        if (l < size && heap[l] < heap[smallest])
            smallest = l;
        if (r < size && heap[r] < heap[smallest])
            smallest = r;
        if (smallest != i) {
            swap(i, smallest);
            heapifyDown(smallest);
        }
    }

    public int size(){
        return size;
    }

    //building heap from an array
    public void buildHeap(int[] arr) {
        if (arr.length > capacity)
            throw new RuntimeException("Array too big");
        size = arr.length;
        System.arraycopy(arr, 0, heap, 0, size);
        for (int i = size / 2 - 1; i >= 0; i--) {
            heapifyDown(i);
        }
    }

    //sort but first make a heap outta the array
    public static void heapSort(int[] arr){
        //Build min-heap from array
        MinHeap heap = new MinHeap(arr.length);
        heap.buildHeap(arr);
        //Repeatedly extract the min and put it back into arr
        for(int i=0; i<arr.length; i++){
            arr[i] = heap.extractMin();
        }
    }

    public static void main(String[] args) {
        int[] data = {5,3,8,1,4,7};
        System.out.println("Before: " + Arrays.toString(data));
        heapSort(data);
        System.out.println("After: " + Arrays.toString(data)); //output ascending
    }
}

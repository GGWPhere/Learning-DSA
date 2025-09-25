//Convert the array to heap
/* Key Idea
 * Tnasform array into a MinHeap
 * Record each swap and prtin:
 *  -Total number of swaps
 *  -Each swaps as a pair of indices 'i j'
 */
/*Algorithm: Bottom up heap construction
 * Start from last internal node: index (n/2)-1
 * For each index 'i' going backwards to 0, sift-down:
 *  -Compare node 'i' with its children
 *  -If a child is smaller, swap with smallest child
 *  -Continue sifting down recursively.
 */

import java.util.*;

 //int [] a
 //List<int[]> swaps - list of pairs of indices
public class ArraytoHeap{
    private int[] heap;
    private List<int[]> swaps;
    public ArraytoHeap(int[] heap){
        this.heap = heap;
        this.swaps = new ArrayList<>();
    }
    //start from last parent node and sift down
    public void minHeap(){
        int n = heap.length;
        for (int i = n/2 -1; i>=0; i--){
            siftDown(i);
        }
    }
    //siftdown
    public void siftDown(int i){
        int n = heap.length;
        int minIndex = i;
        while (true) { 
            int left = 2*i+1;
            int right = 2 *i+2;
            minIndex = i;  
            if(left<n && heap[left] < heap[minIndex])
                minIndex = left;
            if(right<n && heap[right] < heap[minIndex])
                minIndex = right;
            if(i!=minIndex){
                //record swap
                swaps.add(new int[]{ i, minIndex});
                //swap indices in array
                int temp = heap[i];
                heap[i] = heap[minIndex];
                heap[minIndex] = temp;
                //continue sifting from child
                i = minIndex;
            }else{
                break; 
            }
        }
    }

    //traverse through the array
    public void printSwaps(){
        System.out.println(swaps.size());
        for(int[] s : swaps){
            System.out.println(s[0] + " " + s[1]);
        }
    }


    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i=0; i<n; i++){
            a[i] = sc.nextInt();
        }
        ArraytoHeap convert = new ArraytoHeap(a);
        convert.minHeap();
        convert.printSwaps();
    }
}